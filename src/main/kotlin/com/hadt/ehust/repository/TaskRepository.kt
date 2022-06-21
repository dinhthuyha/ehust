package com.hadt.ehust.repository

import com.hadt.ehust.entities.Task
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TaskRepository: JpaRepository<Task, Int> {
    @Query(value = "select * from task where task.id_topic = :id_topic", nativeQuery = true)
    fun findByIdTopic(@Param("id_topic") idTopic: Int): Optional<List<Task>>
}