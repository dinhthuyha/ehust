package com.hadt.ehust.service

import com.hadt.ehust.entities.Attachment
import com.hadt.ehust.entities.Task
import com.hadt.ehust.entities.copy
import com.hadt.ehust.model.StatusTask
import com.hadt.ehust.repository.AttachmentRepository
import com.hadt.ehust.repository.TaskRepository
import com.hadt.ehust.repository.TopicRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class TaskService(
    private val topicRepository: TopicRepository,
    private val taskRepository: TaskRepository,
    private val attachmentRepository: AttachmentRepository
) {

    fun findAllTaskByIdTopic(idTopic: Int): ResponseEntity<List<Task>> {
        return taskRepository.findByIdTopic(idTopic)
            .map {
                ResponseEntity.ok().body(it)
            }.orElse(ResponseEntity.notFound().build())
    }

    fun newTask(topicId: Int, task: Task): ResponseEntity<Any> {
        return topicRepository.findByIdOrNull(topicId)?.let {
            taskRepository.save(task.copy(id = null, topics = it, status = StatusTask.NEW)).id
        }?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.internalServerError().build()
    }

    fun updateTask(task: Task): ResponseEntity<HttpStatus> {
        return taskRepository.findByIdOrNull(task.id!!)?.let {
            val title = task.title ?: it.title
            val des = task.description ?: it.description
            val startDate = task.startDate ?: it.startDate
            val dueDate = task.dueDate ?: it.dueDate
            val done = task.progress ?: it.progress
            val estimateTime = task.estimateTime ?: it.estimateTime
            val spendTime = task.spendTime ?: it.spendTime
            val assignee = task.assignee ?: it.assignee

            val t = it.copy(
                title = title,
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