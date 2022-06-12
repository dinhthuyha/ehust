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

    /**
     * danh sach sinh vien hoc trong lop co id class
     */
    @GetMapping("/search/class/project/{id}")
    fun findAllUserClass(@PathVariable(value = "id") id: Int) = classService.findAllClassProject(id)

    /**
     * danh sach project sinh vien toan truong hoc trong ki nay
     */
    @GetMapping("project/current/semester")
    fun findAllProjectCurrentSemester() = classService.findAllProjectCurrentSemester()

    /**
     * Tim tat cac user trong lop hoc ki B va hoc mon A
     *
     */
    @GetMapping("project/users/{semester}/{nameCourse}")
    fun findAllUserInClass(@PathVariable(value = "semester") semester: Int, @PathVariable(value = "nameCourse") nameCourse: String) =
        classService.findAllUserInClass(semester, nameCourse)
}