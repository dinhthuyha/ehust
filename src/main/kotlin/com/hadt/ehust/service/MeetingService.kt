package com.hadt.ehust.service

import com.hadt.ehust.entities.Meeting
import com.hadt.ehust.entities.copy
import com.hadt.ehust.model.Role
import com.hadt.ehust.repository.MeetingRepository
import com.hadt.ehust.repository.UserRepository
import com.hadt.ehust.utils.Utils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class MeetingService(private val meetingRepository: MeetingRepository, private val userRepository: UserRepository) {

    fun postMeeting(meeting: Meeting): ResponseEntity<List<Meeting>>{
        val idStudent = userRepository.findByFullName(meeting.nameUserStudent)?.id
        var a = meeting
        a.idUserStudent = idStudent
        meetingRepository.save(a)
        val meetings = mutableListOf<Meeting>()
        a.idUserStudent?.let { a.idUserTeacher?.let { it1 -> getAllMeeting(it, it1) } }?.let { meetings.addAll(it) }
        return ResponseEntity.ok().body(meetings)
    }

    fun findAllMeeting(idUserStudent: Int , idUserTeacher: Int): ResponseEntity<List<Meeting>>{
        val meetings = mutableListOf<Meeting>()
       meetings.addAll(getAllMeeting(idUserStudent, idUserTeacher))
       return ResponseEntity.ok().body(meetings)
    }

    private fun getAllMeeting(idUserStudent: Int , idUserTeacher: Int): List<Meeting>{
        return  when(Utils.hasRole(Role.ROLE_STUDENT)){
            true -> {meetingRepository.findByIdUserStudent(idUserStudent)}

            false -> { meetingRepository.findByIdUserTeacher(idUserTeacher)}
        }
    }
}