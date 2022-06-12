package com.hadt.ehust.service

import com.hadt.ehust.entities.User
import com.hadt.ehust.model.Role
import com.hadt.ehust.repository.SubjectRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class SubjectService( private val subjectRepository: SubjectRepository) {

    fun findAllUserInClass(semester: Int, nameCourse: String, role: Role): ResponseEntity<List<User>> {
        val users = mutableListOf<User>()
        return subjectRepository.findAllUserInClass( nameCourse).map { sub ->
            sub.listClass?.filter { it.semester == semester }?.forEach { itemClass ->
                itemClass.likes?.toList()?.filter { it.role == role }?.forEach {
                    users.add(User(
                        id = it.id,
                        fullName = it.fullName
                    ))
                }
            }
            ResponseEntity.ok(users.toList())
        }.orElse(ResponseEntity.notFound().build())
    }

}