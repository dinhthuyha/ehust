package com.hadt.ehust.controller

import com.hadt.ehust.entities.Comments
import com.hadt.ehust.service.CommentService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class CommentController( private val commentService: CommentService) {

    @GetMapping("comments/{id_task}")
    fun findAllCommentByIdTask(@PathVariable("id_task") idTask: Int) = commentService.findAllCommentByIdTask(idTask)

    @PostMapping("postComment")
    fun postComment(@RequestBody comment: Comments) = commentService.postComment(comment)

    @DeleteMapping("deleteComment")
    fun deleteCommentById(@PathVariable("id") id: Int) = commentService.deleteComment(id)
}