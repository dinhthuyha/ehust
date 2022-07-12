package com.hadt.ehust.controller

import com.hadt.ehust.entities.Meeting
import com.hadt.ehust.service.MeetingService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class MeetingController (private val meetingService: MeetingService){

    @PostMapping("metting")
    fun postMeeting(@RequestBody meeting: Meeting) = meetingService.postMeeting(meeting)

    @GetMapping("all/meeting/{id_user_teacher}/{id_user_student}")
    fun findAllMeeting(@PathVariable(value ="id_user_teacher") idUserTeacher: Int, @PathVariable(value = "id_user_student") idUserStudent: Int) = meetingService.findAllMeeting(idUserStudent , idUserTeacher)
}