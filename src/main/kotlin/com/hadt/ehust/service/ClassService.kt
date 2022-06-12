package com.hadt.ehust.service

import com.hadt.ehust.entities.ClassStudent
import com.hadt.ehust.entities.Subject
import com.hadt.ehust.entities.User
import com.hadt.ehust.repository.ClassStudentRepository

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ClassService(private val classStudentRepository: ClassStudentRepository) {
    fun findById(id: Int): ResponseEntity<ClassStudent> {
        return classStudentRepository.findById(id).map {
            ResponseEntity.ok(
               ClassStudent(
                   codeClass = it.codeClass,
                   semester = it.semester,
                   studyForm = it.studyForm,
                   subjectClass = Subject(
                       it.subjectClass?.id!!,
                       it.subjectClass?.name
                   )
               )
            )
        }.orElse(ResponseEntity.notFound().build())
    }

    //find all user in one class
    fun findAllClassProject(id: Int): ResponseEntity<List<User>> {
        val projects = mutableListOf<User>()
        return classStudentRepository.findById(id).map { classStu ->
            classStu.likes?.toList()?.forEach {
                val item = User(
                    id = it.id,
                    fullName = it.fullName,
                    instituteOfManagement = it.instituteOfManagement,
                    gender = it.gender,
                    grade = it.grade,
                    course = it.course,
                    email = it.email,
                    cadreStatus = it.cadreStatus ?: "",
                    unit = it.unit,
                    role = it.role,
                    imageAvatar = it.imageAvatar,
                    imageBackground = it.imageBackground
                )
                projects.add(item)

            }
            ResponseEntity.ok(projects.toList())
        }.orElse(ResponseEntity.notFound().build())
    }


    fun findAllProjectCurrentSemester(): ResponseEntity<List<ClassStudent?>> {
        val newProjects = mutableListOf<ClassStudent>()
        val projects = classStudentRepository.findAll()
        val semesterCurrent = projects.maxOf { it.semester!! }
        projects.filter { it.subjectClass?.isProject ==true && it?.semester == semesterCurrent }.forEach {

            newProjects.add(
                ClassStudent(
                    codeClass = it.codeClass,
                    subjectClass = Subject(
                        it.subjectClass?.id!!,
                        it.subjectClass.name
                    )
                )
            )
        }

        return ResponseEntity.ok(newProjects.distinct())
    }




}

