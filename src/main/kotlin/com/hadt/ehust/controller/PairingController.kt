package com.hadt.ehust.controller

import com.hadt.ehust.model.Role
import com.hadt.ehust.service.PairingService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.annotation.security.RolesAllowed

@RestController
@RequestMapping("/api")
class PairingController(private val pairingService: PairingService) {

    @PostMapping("/assignProject")
    fun assignProjectInstructions(
        @RequestParam(name = "id_student") idStudent: Int,
        @RequestParam(name = "id_teacher") idTeacher: Int,
        @RequestParam(name = "name_project") nameProject: String,
    ) =
        pairingService.assignProjectInstructions(idStudent, idTeacher, nameProject)

    @GetMapping("all/semester")
    fun getAllSemester() = pairingService.getAllSemester()

    @GetMapping("project/teacher/{id_teacher}/{semester}")
    fun getAllProjectByIdTeacherAndSemester(
        @PathVariable("id_teacher") idTeacher: Int,
        @PathVariable("semester") semester: Int
    ) =
        pairingService.getAllProjectByIdTeacherAndSemester(idTeacher, semester)
    @RolesAllowed(Role.ADMIN)
    @GetMapping("information/dashboard")
    fun getInformationDashBoard() = pairingService.getInformation()
}