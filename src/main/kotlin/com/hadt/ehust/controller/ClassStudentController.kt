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
}