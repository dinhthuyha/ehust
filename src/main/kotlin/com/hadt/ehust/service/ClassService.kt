package com.hadt.ehust.service

import com.hadt.ehust.repository.ClassStudentRepository
import org.springframework.stereotype.Service

@Service
class ClassService( private val classStudentRepository: ClassStudentRepository) {
    fun findById(id: Int) = classStudentRepository.findById(id)
}