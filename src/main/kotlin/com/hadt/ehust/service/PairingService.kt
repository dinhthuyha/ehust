package com.hadt.ehust.service

import com.hadt.ehust.entities.PairingTeacherWithStudent
import com.hadt.ehust.repository.PairingRepository
import org.springframework.stereotype.Service

@Service
class PairingService(private val pairingRepository: PairingRepository){

    fun assignProjectInstructions( idStudent: Int, idTeacher: Int, nameProject: String){
        pairingRepository.save(PairingTeacherWithStudent(idStudent = idStudent, idTeacher = idTeacher, nameProject = nameProject))
    }

}