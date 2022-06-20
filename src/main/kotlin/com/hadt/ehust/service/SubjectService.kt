package com.hadt.ehust.service

import com.hadt.ehust.entities.User
import com.hadt.ehust.model.Role
import com.hadt.ehust.repository.SubjectRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class SubjectService( private val subjectRepository: SubjectRepository) {

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
                        users.add(User(
                            id = it.id,
                            fullName = it.fullName
                        ))
                    }
                }
            }

            ResponseEntity.ok(users.toList())
        }.orElse(ResponseEntity.notFound().build())
    }

}