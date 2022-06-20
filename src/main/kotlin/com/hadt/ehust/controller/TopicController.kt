package com.hadt.ehust.controller

import com.hadt.ehust.model.Role
import com.hadt.ehust.model.StatusTopic
import com.hadt.ehust.repository.PairingRepository
import com.hadt.ehust.service.TopicService
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api")
class TopicController(
    private val topicService: TopicService,
    private val pairingRepository: PairingRepository
){

    /**
     * update status cho topic or idStudent or cả hai
     */
    @PutMapping("updatetopic/{id_topic}")
    fun updateTopicStatus(@PathVariable(value = "id_topic") idTopic: Int,
                          @RequestParam(value = "status") status: StatusTopic,
                          @RequestParam(value = "id_student") idStudent: Int,
                         )=
        topicService.updateTopicStatus( idTopic, status, idStudent)

    /**
     * xem ds topic theo (id_gv, ten do an)
     */
    @GetMapping("topic/teacher/{id_teacher}/{name_teacher}/{id_project}")
    fun findTopicByNameProjectAndIdTeacher(
        @PathVariable(value = "name_teacher") nameTeacher: String,
        @PathVariable(value = "id_project") idProject: String,
        @PathVariable(value = "id_teacher") idTeacher: Int
    ) = topicService.findTopicByNameProjectAndIdTeacher(nameTeacher, idProject, idTeacher)
}