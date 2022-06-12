package com.hadt.ehust.controller

import com.hadt.ehust.model.Role
import com.hadt.ehust.service.SubjectService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class SubjectController( private val subjectService: SubjectService) {

    /**
     * Tim tat cac user trong lop hoc ki B va hoc mon A
     *
     */
    @GetMapping("project/users/{semester}/{nameCourse}")
    fun findAllUserInClass(@PathVariable(value = "semester") semester: Int, @PathVariable(value = "nameCourse") nameCourse: String, @PathVariable(value = "role") role: Role) =
        subjectService.findAllUserInClass(semester, nameCourse, role)
}