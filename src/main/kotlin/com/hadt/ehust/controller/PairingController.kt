package com.hadt.ehust.controller

import com.hadt.ehust.service.PairingService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class PairingController( private val pairingService: PairingService) {

    @PostMapping("/assignProject")
    fun assignProjectInstructions(
        @RequestParam(name = "id_student") idStudent: Int,
        @RequestParam(name = "id_teacher") idTeacher: Int,
        @RequestParam(name = "name_project") nameProject: String) =
        pairingService.assignProjectInstructions(idStudent, idTeacher, nameProject)

    @GetMapping("all/semester")
    fun getAllSemester()= pairingService.getAllSemester()
}