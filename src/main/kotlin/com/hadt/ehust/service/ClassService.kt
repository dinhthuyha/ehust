package com.hadt.ehust.service

import com.hadt.ehust.repository.ClassStudentRepository
import com.hadt.ehust.response.ProjectResponse
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ClassService( private val classStudentRepository: ClassStudentRepository) {
    fun findById(id: Int): ResponseEntity<ProjectResponse> {
        return classStudentRepository.findById(id).map {
            ResponseEntity.ok(
                ProjectResponse(
                    codeClass = it.codeClass,
                    codeCourse = it.codeCourse,
                    nameCourse = it.nameCourse,
                    semester = it.semester,
                    nameTeacher = it.nameTeacher?:"",
                    studyForm = it.studyForm)
            )
        }.orElse(ResponseEntity.notFound().build())
    }
}