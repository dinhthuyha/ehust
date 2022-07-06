package com.hadt.ehust.repository

import com.hadt.ehust.entities.Attachment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface AttachmentRepository: JpaRepository<Attachment, Int> {

    @Query(value = "select * from attachments a where a.id_task = ?1", nativeQuery = true)
    fun findByIdTask(idTask: Int): List<Attachment>
}