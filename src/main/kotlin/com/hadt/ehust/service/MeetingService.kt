package com.hadt.ehust.service

import com.hadt.ehust.entities.Meeting
import com.hadt.ehust.model.Role
import com.hadt.ehust.repository.MeetingRepository
import com.hadt.ehust.repository.UserRepository
import com.hadt.ehust.utils.Utils
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class MeetingService(private val meetingRepository: MeetingRepository, private val userRepository: UserRepository) {

    fun postMeeting(meeting: Meeting): ResponseEntity<List<Meeting>>{
        val idStudent = userRepository.findByFullName(meeting.nameStudent)?.id
        var a = meeting
        a.idStudent = idStudent
        meetingRepository.save(a)
        val meetings = mutableListOf<Meeting>()
        a.idStudent?.let { a.idTeacher?.let { it1 -> getAllMeeting(it, it1) } }?.let { meetings.addAll(it) }
        return ResponseEntity.ok().body(meetings)
    }

    fun findAllMeeting(idUserStudent: Int , idUserTeacher: Int): ResponseEntity<List<Meeting>>{
        val meetings = mutableListOf<Meeting>()
       meetings.addAll(getAllMeeting(idUserStudent, idUserTeacher))
       return ResponseEntity.ok().body(meetings)
    }

    private fun getAllMeeting(idStudent: Int, idUserTeacher: Int): List<Meeting>{
        return  when(Utils.hasRole(Role.ROLE_STUDENT)){
            true -> {meetingRepository.findByIdStudent(idStudent)}

            false -> { meetingRepository.findByIdTeacher(idUserTeacher)}
        }
    }
}