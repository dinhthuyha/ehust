package com.hadt.ehust.repository

import com.hadt.ehust.entities.Subject
import com.hadt.ehust.entities.Topic
import com.hadt.ehust.model.StatusTopic
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TopicRepository : JpaRepository<Topic,Int> {
    fun findByName(name:String):Topic?
    fun findByIdTeacher(idTeacher: Int): List<Topic>?

    fun findByIdStudent(idStudent: Int): Topic?
}