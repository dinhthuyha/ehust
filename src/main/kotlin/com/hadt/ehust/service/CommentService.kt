package com.hadt.ehust.service

import com.hadt.ehust.entities.Comments
import com.hadt.ehust.entities.Task
import com.hadt.ehust.repository.CommentRepository
import com.hadt.ehust.repository.TaskRepository
import com.hadt.ehust.repository.UserRepository
import com.hadt.ehust.utils.Utils
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class CommentService(
    private val commentRepository: CommentRepository,
    private val userRepository: UserRepository,
    private val taskRepository: TaskRepository
) {

    fun findAllCommentByIdTask(idTask: Int): ResponseEntity<List<Comments>> {
        var comments = mutableListOf<Comments>()
        commentRepository.findByIdTask(idTask).let { cmts ->
            cmts.forEach { item ->
                userRepository.findById(item.idUser).map { user ->
                    item.nameUserPost = user.fullName
                }
            }
            comments.addAll(cmts)
        }
        return if (comments.isEmpty()) {
            ResponseEntity.notFound().build()
        } else {
            ResponseEntity.ok().body(comments)
        }
    }

    fun postComment(idTask: Int, cmt: Comments): ResponseEntity<List<Comments>> {
        cmt.idUser = Utils.getCurrentUserId()
        cmt.task = taskRepository.findByIdOrNull(idTask)
        commentRepository.save(cmt)
        return findAllCommentByIdTask(idTask)
    }

    fun deleteComment(cmtId: Int): ResponseEntity<HttpStatus> {
        commentRepository.deleteById(cmtId)
        return ResponseEntity.ok(HttpStatus.OK)
    }
}