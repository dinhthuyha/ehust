package com.hadt.ehust.service

import com.hadt.ehust.entities.Attachment
import com.hadt.ehust.entities.Comments
import com.hadt.ehust.entities.Task
import com.hadt.ehust.repository.AttachmentRepository
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
    private val taskRepository: TaskRepository,
    private val attachmentRepository: AttachmentRepository
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

    fun postComment(idTask: Int, cmt: Comments): ResponseEntity<Int> {
        cmt.idUser = Utils.getCurrentUserId()
        cmt.task = taskRepository.findByIdOrNull(idTask)
        val savedCmt = commentRepository.save(cmt)
        return ResponseEntity.ok(savedCmt.id)
    }

    fun addAttachment(idCmt: Int, attachment: Attachment): ResponseEntity<Any> {
        return commentRepository.findByIdOrNull(idCmt)?.let {
            attachment.comment = it
            attachmentRepository.save(attachment)
            ResponseEntity.ok("ok")
        } ?: ResponseEntity.badRequest().build()
    }


    fun deleteComment(cmtId: Int): ResponseEntity<HttpStatus> {
        commentRepository.deleteById(cmtId)
        return ResponseEntity.ok(HttpStatus.OK)
    }
}