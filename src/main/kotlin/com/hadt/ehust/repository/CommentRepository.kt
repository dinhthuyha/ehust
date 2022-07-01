package com.hadt.ehust.repository

import com.hadt.ehust.entities.Comments
import com.hadt.ehust.entities.Task
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.Optional

interface CommentRepository: JpaRepository<Comments, Int> {

    @Query(value = "select * from comments c where c.id_task = :id_task", nativeQuery = true)
    fun findByIdTask(@Param("id_task")idTask: Int): List<Comments>
}