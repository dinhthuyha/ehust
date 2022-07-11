package com.hadt.ehust.controller

import com.hadt.ehust.entities.Attachment
import com.hadt.ehust.entities.Task
import com.hadt.ehust.service.TaskService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class TaskController( private val taskService: TaskService) {

    @GetMapping("allTask/idTopic/{id_topic}")
    fun findAllTaskByIdTopic(@PathVariable("id_topic") idTopic: Int) =
        taskService.findAllTaskByIdTopic(idTopic)

    @PutMapping("updateTask")
    fun updateTask(@RequestBody task: Task)=
        taskService.updateTask(task)

    @PostMapping("newTask")
    fun newTask(@RequestParam("task") task: Task) =
        taskService.newTask(task)

    @GetMapping("task/{id}")
    fun findByIdTask(@PathVariable("id") id: Int)= taskService.findByIdTask(id)

    @GetMapping("/task/{id}/attachment")
    fun getAttachments(@PathVariable("id") id: Int): ResponseEntity<List<Attachment>> {
        val attachments = taskService.findAllAttachment(id)
        return ResponseEntity.ok(attachments)
    }

    @PostMapping("task/{id}/attachment")
    fun addAttachment(@PathVariable("id") id: Int, @RequestBody attachment: Attachment): ResponseEntity<List<Attachment>> {
        val attachments = taskService.addAttachment(id, attachment)
        return ResponseEntity.ok(attachments)
    }

    /**
     * lít task se het han trong 2 ngay toi.
     */
    @GetMapping("task/willexpire")
    fun findAllTaskWillExpire() = taskService.findAllTaskWillExpire()
}