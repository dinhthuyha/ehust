package com.hadt.ehust.controller

import com.hadt.ehust.entities.Meeting
import com.hadt.ehust.service.MeetingService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class MeetingController (private val meetingService: MeetingService){

    @PostMapping("metting")
    fun postMeeting(@RequestBody meeting: Meeting) = meetingService.postMeeting(meeting)
}