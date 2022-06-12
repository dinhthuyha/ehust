package com.hadt.ehust.service

import com.hadt.ehust.repository.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ShareService(
    private val pairingRepository: PairingRepository,
    private val topicRepository: TopicRepository,
    private val classStudentRepository: ClassStudentRepository,
    private val userRepository: UserRepository,
    private val subjectRepository: SubjectRepository
) {

    fun updateTopicAssign(idTeacher: Int, idStudent: Int, nameProject: String): ResponseEntity<Unit>{
        var nameTeacher = ""
        userRepository.findById(idTeacher).map {
           nameTeacher = it.fullName
        }
       return userRepository.findById(idStudent).map { user ->
             user.userSubjects
                ?.toList()?.firstOrNull { it.isProject == true && it.name == nameProject }
                ?.listClass
                ?.firstOrNull()
                 ?.let { oldClass ->
                     val newClass = oldClass.copy(nameTeacher = nameTeacher)
                 }

           ResponseEntity<Unit>(HttpStatus.OK)

        }.orElse(ResponseEntity.notFound().build())
    }
}