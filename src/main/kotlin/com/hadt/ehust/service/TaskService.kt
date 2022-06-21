package com.hadt.ehust.service

import com.hadt.ehust.entities.Task
import com.hadt.ehust.entities.Topic
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
        taskRepository.save(task)
        return ResponseEntity.ok(HttpStatus.OK)
    }
}