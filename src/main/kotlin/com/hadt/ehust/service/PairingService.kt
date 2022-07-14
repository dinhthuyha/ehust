package com.hadt.ehust.service

import com.hadt.ehust.entities.PairingTeacherWithStudent
import com.hadt.ehust.repository.ClassStudentRepository
import com.hadt.ehust.repository.PairingRepository
import com.hadt.ehust.repository.SubjectRepository
import com.hadt.ehust.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class PairingService(
    private val pairingRepository: PairingRepository,
    private val userRepository: UserRepository,
    private val subjectRepository: SubjectRepository
) {

    fun assignProjectInstructions(idStudent: Int, idTeacher: Int, nameProject: String): ResponseEntity<HttpStatus> {
        var semester: Int? = 0
        userRepository.findById(idStudent).map {
           semester = it.likedClasses?.find { it.subjectClass?.name == nameProject }?.semester
        }
        pairingRepository.save(
            PairingTeacherWithStudent(
                idStudent = idStudent,
                idTeacher = idTeacher,
                nameProject = nameProject,
                semester = semester
            )
        )
        return ResponseEntity.ok(HttpStatus.OK)
    }

    fun getAllProjectByIdTeacherAndSemester(idTeacher: Int, semester: Int): ResponseEntity<List<PairingTeacherWithStudent>>{
        val list = pairingRepository.findByIdTeacher(idTeacher)?.filter { it.semester == semester }
        list?.forEach {pairing ->
            subjectRepository.findByName(pairing.nameProject)?.let { pairing.codeProject = it.id }
             userRepository.findById(pairing.idStudent).map {
                 pairing.nameStudent = it.fullName
             }
        }
        return ResponseEntity.ok().body(list)
    }
    fun getAllSemester():ResponseEntity<List<Int>>{
        val semsters = pairingRepository.findAll().map { it.semester!! }.distinct().sortedDescending()
        return ResponseEntity.ok().body(semsters)

    }
}