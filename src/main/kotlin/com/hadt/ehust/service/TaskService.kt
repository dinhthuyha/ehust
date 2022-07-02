package com.hadt.ehust.service

import com.hadt.ehust.entities.Task
import com.hadt.ehust.entities.Topic
import com.hadt.ehust.entities.copy
import com.hadt.ehust.repository.TaskRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class TaskService(private val taskRepository: TaskRepository) {

    fun findAllTaskByIdTopic(idTopic: Int): ResponseEntity<List<Task>> {
        return taskRepository.findByIdTopic(idTopic)
            .map {
                ResponseEntity.ok().body(it)
            }.orElse(ResponseEntity.notFound().build())
    }

    fun newTask(task: Task): ResponseEntity<HttpStatus> {
        taskRepository.save(task)
        return ResponseEntity.ok(HttpStatus.OK)
    }

    fun updateTask(task: Task): ResponseEntity<HttpStatus> {
        return taskRepository.findById(task.id!!).map {
            val des = task.description ?: it.description
            val startDate = task.startDate ?: it.startDate
            val dueDate = task.dueDate ?: it.dueDate
            val done = task.progress ?: it.progress
            val estimateTime = task.estimateTime ?: it.estimateTime
            val spendTime = task.spendTime ?: it.spendTime
            val assignee = task.assignee ?: it.assignee
            it.copy(
                description = des,
                startDate = startDate,
                dueDate = dueDate,
                progress = done,
                estimateTime = estimateTime,
                spendTime = spendTime,
                assignee = assignee
            ).let {
                taskRepository.save(it)
            }

             ResponseEntity.ok(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())

    }

    fun findByIdTask(id: Int): ResponseEntity<Task> {
        return taskRepository.findById(id).map {
            ResponseEntity.ok().body(it)
        }.orElse(ResponseEntity.notFound().build())
    }


}