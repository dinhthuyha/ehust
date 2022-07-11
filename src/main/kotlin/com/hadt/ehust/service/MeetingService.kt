package com.hadt.ehust.service

import com.hadt.ehust.entities.Meeting
import com.hadt.ehust.repository.MeetingRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class MeetingService(private val meetingRepository: MeetingRepository) {

    fun postMeeting(meeting: Meeting): ResponseEntity<HttpStatus>{
        meetingRepository.save(meeting)
        return ResponseEntity.ok().body(HttpStatus.OK)
    }
}