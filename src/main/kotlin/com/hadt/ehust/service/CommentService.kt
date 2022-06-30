package com.hadt.ehust.service

import com.hadt.ehust.entities.Comments
import com.hadt.ehust.entities.Task
import com.hadt.ehust.repository.CommentRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class CommentService( private val commentRepository: CommentRepository) {

    fun findAllCommentByIdTask(idTask: Int): ResponseEntity<List<Comments>>{
       return commentRepository.findByIdTask(idTask).map {
           ResponseEntity.ok().body(it)
       }.orElse(ResponseEntity.notFound().build())
    }

    fun postComment(cmt: Comments): ResponseEntity<HttpStatus> {
        commentRepository.save(cmt)
        return ResponseEntity.ok(HttpStatus.OK)
    }

    fun deleteComment(cmtId: Int): ResponseEntity<HttpStatus>{
        commentRepository.deleteById(cmtId)
        return ResponseEntity.ok(HttpStatus.OK)
    }
}