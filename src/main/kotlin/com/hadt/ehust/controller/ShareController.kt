package com.hadt.ehust.controller

import com.hadt.ehust.service.ShareService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class ShareController( private val shareService: ShareService) {
    
    /**
     * update assign tacher cho sv
     */
    @PutMapping("update/assign/project/{id_teacher}/{id_student}/{name_course}")
    fun updateTopicAssign(
        @PathVariable(value = "id_teacher") idTeacher: Int,
        @PathVariable(value = "id_student") idStudent: Int,
        @PathVariable(value = "name_course") name: String
    ) = shareService.updateTopicAssign(idTeacher, idStudent, name)
}