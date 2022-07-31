package com.hadt.ehust.service

import com.hadt.ehust.entities.Attachment
import com.hadt.ehust.entities.Task
import com.hadt.ehust.entities.copy
import com.hadt.ehust.model.StatusTask
import com.hadt.ehust.repository.AttachmentRepository
import com.hadt.ehust.model.StatusTopic
import com.hadt.ehust.repository.TaskRepository
import com.hadt.ehust.repository.TopicRepository
import org.springframework.data.repository.findByIdOrNull
import com.hadt.ehust.repository.UserRepository
import com.hadt.ehust.utils.Utils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.Period

@Service
class TaskService(
    private val topicRepository: TopicRepository,
    private val taskRepository: TaskRepository,
    private val attachmentRepository: AttachmentRepository,
    private val userRepository: UserRepository
) {

    fun findAllTaskByIdTopic(idTopic: Int): ResponseEntity<List<Task>> {
        return taskRepository.findByIdTopic(idTopic)
            .map {
                ResponseEntity.ok().body(it)
            }.orElse(ResponseEntity.notFound().build())
    }

    fun newTask(topicId: Int, task: Task): ResponseEntity<Any> {
        return topicRepository.findByIdOrNull(topicId)?.let {
            taskRepository.save(task.copy(id = null, topics = it)).id
        }?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.internalServerError().build()
    }

    fun updateTask(task: Task): ResponseEntity<HttpStatus> {
        return taskRepository.findByIdOrNull(task.id!!)?.let {
            val title = task.title ?: it.title
            val status = task.status ?: it.status
            val des = task.description ?: it.description
            val startDate = task.startDate ?: it.startDate
            val dueDate = task.dueDate ?: it.dueDate
            val done = task.progress ?: it.progress
            val estimateTime = task.estimateTime ?: it.estimateTime
            val spendTime = task.spendTime ?: it.spendTime
            val assignee = task.assignee ?: it.assignee

            val t = it.copy(
                title = title,
                status = status,
                description = des,
                startDate = startDate,
                dueDate = dueDate,
                progress = done,
                estimateTime = estimateTime,
                spendTime = spendTime,
                assignee = assignee
            ).let(taskRepository::save)

             ResponseEntity.ok(HttpStatus.OK)
        } ?: ResponseEntity.notFound().build()
    }

    fun findByIdTask(id: Int): ResponseEntity<Task> {
        return taskRepository.findById(id).map {
            ResponseEntity.ok().body(it)
        }.orElse(ResponseEntity.notFound().build())
    }

    fun findAllTaskWillExpire(): ResponseEntity<List<Task>> {
        var idTopics = getListTopicCurrentSemester()
        val listTask = mutableListOf<Task>()
        idTopics?.forEach { id ->
            taskRepository.findAllTaskWillExpire(id)?.let {
                listTask.addAll(it.filter { it.showTimeRemain()!=null && it.showTimeRemain()!! <3 })
            }
        }
        return if (listTask.isNotEmpty()) ResponseEntity.ok().body(listTask) else ResponseEntity.notFound().build()

    }

    private fun getListTopicCurrentSemester(): List<Int>? {
        var idTopics = mutableListOf<Int>()
        val idUser = Utils.getCurrentUserId()
        userRepository.findById(idUser).map { user ->
            val semester = user.likedClasses?.maxOf { it.semester ?: 0 }
            user.likedClasses?.let { listClass ->
                val targetClasses = listClass.filter { it.semester == semester }
                targetClasses.forEach { cla ->
                    cla.subjectClass?.let {
                        if (it.isProject == true) {
                            it.topics
                                ?.filter { topic -> topic.status == StatusTopic.ACCEPT && Utils.getCurrentUserId() == topic.idStudent }
                                ?.map { it.id!! }
                                ?.let {
                                    idTopics.addAll(it)
                                }
                        }
                    }
                }
            }
        }
        return idTopics
    }

    private fun Task.showTimeRemain(): Int? {
        if (this.status == StatusTask.IN_PROGRESS) {
            val today = LocalDate.now()
            val dueDate = this.dueDate
            val dateRemain = Period.between(today, dueDate).days
            return dateRemain
        }

        return null
    }

    fun addAttachment(idTask: Int, attachment: Attachment): List<Attachment> {
        val task = taskRepository.findByIdOrNull(idTask)
        attachment.task = task
        attachmentRepository.save(attachment)
        return attachmentRepository.findByIdTask(idTask)
    }

    fun findAllAttachment(id: Int): List<Attachment> {
        return attachmentRepository.findByIdTask(id)
    }


}