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

    fun postMeeting(meeting: Meeting): ResponseEntity<HttpStatus>{
        val idStudent = userRepository.findByFullName(meeting.nameStudent)?.id
        var a = meeting
        a.idUserStudent = idStudent
        meetingRepository.save(a)
        return ResponseEntity.ok().body(HttpStatus.OK)
    }

    fun findAllMeeting(idUserStudent: Int , idUserTeacher: Int): ResponseEntity<List<Meeting>>{
        val meetings = mutableListOf<Meeting>()
        when(Utils.hasRole(Role.ROLE_STUDENT)){
            true -> {meetings.addAll(meetingRepository.findByIdUserStudent(idUserStudent))}

            false -> { meetings.addAll(meetingRepository.findByIdUserTeacher(idUserTeacher))}
        }
       return ResponseEntity.ok().body(meetings)
    }
}