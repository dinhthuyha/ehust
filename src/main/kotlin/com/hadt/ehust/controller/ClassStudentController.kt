package com.hadt.ehust.controller

import com.hadt.ehust.model.Role
import com.hadt.ehust.service.ClassService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.security.RolesAllowed

@RestController
@RequestMapping("/api")
class ClassStudentController(private val classService: ClassService) {

    @GetMapping("/search/class/{id}")
    fun findClassById(@PathVariable(value = "id") id: Int) = classService.findById(id)

    /**
     * danh sach sinh vien hoc trong lop co id class
     */
    @GetMapping("/search/class/project/{id}")
    fun findAllUserClass(@PathVariable(value = "id") id: Int) = classService.findAllClassProject(id)

    /**
     * danh sach project sinh vien toan truong hoc trong ki nay
     */

    @RolesAllowed(Role.ADMIN)
    @GetMapping("project/current/semester")
    fun findAllProjectCurrentSemester() = classService.findAllProjectCurrentSemester()



}