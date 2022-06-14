package com.hadt.ehust.controller

import com.hadt.ehust.model.StatusTopic
import com.hadt.ehust.service.TopicService
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api")
class TopicController( private val topicService: TopicService){

    /**
     * update status cho topic
     */
    @PutMapping("updatetopic/{id_student}/{id_project}")
    fun updateTopicStatus(@PathVariable(value = "id_student") idStudent: Int,
                          @RequestParam(value = "status")status: StatusTopic,
                          @PathVariable(value = "id_project") idProject: String)=
        topicService.updateTopicStatus( idProject, status )

    /**
     * xem ds topic theo (id_gv, ten do an)
     */
    @GetMapping("topic/teacher/{id_teacher}/{id_project}")
    fun findTopicByNameProjectAndIdTeacher(
        @PathVariable(value = "id_teacher") idTeacher: Int,
        @PathVariable(value = "id_project") idProject: String
    ) = topicService.findTopicByNameProjectAndIdTeacher(idTeacher, idProject)
}