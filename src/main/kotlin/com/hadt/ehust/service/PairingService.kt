package com.hadt.ehust.service

import com.hadt.ehust.entities.PairingTeacherWithStudent
import com.hadt.ehust.entities.User
import com.hadt.ehust.model.Role
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
    private val subjectRepository: SubjectRepository,
    private val classStudentRepository: ClassStudentRepository
) {

    fun assignProjectInstructions(idStudent: Int, idTeacher: Int, nameProject: String): ResponseEntity<HttpStatus> {
        val semester = classStudentRepository.findAll().map { it.semester }.maxOf { it?:0 }
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

    fun getInformation(): ResponseEntity<DashBoard>{
        val semesterCurrent = classStudentRepository.findAll().map { it.semester }.maxOf { it?:0 }
        val projects = classStudentRepository.findBySemester(semesterCurrent)?.filter { it.subjectClass?.isProject == true }
        val listStudent = mutableListOf<String>()

        projects?.forEach {  it.likes?.filter { it.role == Role.ROLE_STUDENT }?.map {
            it.fullName
        }?.let { listStudent.addAll(it) } }
        val numberTeacher = userRepository.findByRole(Role.ROLE_TEACHER)
        val information = DashBoard(semesterCurrent, projects?.distinctBy { it.subjectClass?.name }?.size?:0,listStudent.distinctBy { it }.size, numberTeacher?.size?:0)
        return ResponseEntity.ok().body(information)
    }

    fun getAllDataBySemester(semester: Int): ResponseEntity<List<PairingTeacherWithStudent>> {
        var nameStudent = ""
        var nameTeacher = ""
        pairingRepository.findBySemester(semester)?.let {
            it.forEach { pairing ->
                userRepository.findById(pairing.idStudent?:0).map {
                    nameStudent = it.fullName
                }
                userRepository.findById(pairing.idTeacher?:0).map {
                    nameTeacher = it.fullName
                }
                pairing.nameStudent = nameStudent
                pairing.nameTeacher = nameTeacher
            }
         return ResponseEntity.ok().body(it)
        }
        return ResponseEntity.notFound().build()
    }

    fun deleteAssigns(list: List<PairingTeacherWithStudent>): ResponseEntity<HttpStatus> {
        pairingRepository.deleteAll(list)
        return ResponseEntity.ok(HttpStatus.OK)
    }

    data class DashBoard(
        val semester: Int,
        val numberProject: Int,
        val numberStudent: Int,
        val numberTeacher: Int
    )
}