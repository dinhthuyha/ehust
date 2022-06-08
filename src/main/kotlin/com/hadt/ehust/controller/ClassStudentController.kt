package com.hadt.ehust.controller

import com.hadt.ehust.service.ClassService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class ClassStudentController(private val classService: ClassService) {

    @GetMapping("/search/class/{id}")
    fun findClassById(@PathVariable(value = "id") id: Int) = classService.findById(id)

    @GetMapping("/search/class/project/{id}")
    fun findAllUserClass(@PathVariable(value = "id") id: Int) = classService.findAllClassProject(id)

    /**
     * return list project in current semester
     */
    @GetMapping("project/current/semester")
    fun findAllProjectCurrentSemester() = classService.findAllProjectCurrentSemester()

    /**
     * find all user in class ( semester, name) with student
     */
    @GetMapping("project/users/{semester}/{nameCourse}")
    fun findAllUserInClass(@PathVariable(value = "semester") semester: Int, @PathVariable(value = "nameCourse") nameCourse: String) =
        classService.findAllUserInClass(semester, nameCourse)
}