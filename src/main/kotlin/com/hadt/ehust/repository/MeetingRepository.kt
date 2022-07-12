package com.hadt.ehust.repository

import com.hadt.ehust.entities.Meeting
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MeetingRepository : JpaRepository<Meeting, Int>{

    fun findByIdUserStudent(idUserStudent: Int): List<Meeting>

    fun findByIdUserTeacher(idUserTeacher: Int):List<Meeting>
}