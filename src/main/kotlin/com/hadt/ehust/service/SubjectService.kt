package com.hadt.ehust.service

import com.hadt.ehust.entities.User
import com.hadt.ehust.model.Role
import com.hadt.ehust.repository.PairingRepository
import com.hadt.ehust.repository.SubjectRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class SubjectService(
    private val subjectRepository: SubjectRepository,
    private val pairingRepository: PairingRepository
) {

    fun findAllUserInClass( nameCourse: String, role: Role): ResponseEntity<List<User>> {
        var users = mutableListOf<User>()
        return subjectRepository.findAllUserInClass(nameCourse).map { sub ->
            if (role == Role.ROLE_TEACHER) {
                sub.userSubject?.toList()?.filter { it.role == role }?.toMutableList()?.let {
                    it.forEach { user ->
                        users.add(
                            User(
                                id = user.id,
                                fullName = user.fullName
                            )
                        )
                    }
                }
            }else if (role == Role.ROLE_STUDENT){
                val semesterCurrent = sub.listClass?.maxOf { it.semester!! }
                sub.listClass?.filter { it.semester == semesterCurrent }?.forEach { itemClass ->
                    itemClass.likes?.toList()?.filter { it.role == role }?.forEach {
                        if (studentIsNotAssigned(it.id, nameCourse)){
                            users.add(User(
                                id = it.id,
                                fullName = it.fullName
                            ))
                        }

                    }
                }
            }

            ResponseEntity.ok(users.toList().distinctBy { it.id })
        }.orElse(ResponseEntity.notFound().build())
    }

    /**
     * chek xem sv A da duoc assign giao vien voi mon project b chưa
     */
    private fun studentIsNotAssigned(idStudent: Int, nameProject: String): Boolean{
       return pairingRepository.findUserByIdStudentAndNameProject(idStudent,nameProject).isEmpty
    }

}