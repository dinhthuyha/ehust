package com.hadt.ehust.repository

import com.hadt.ehust.entities.ClassStudent
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ClassStudentRepository : JpaRepository<ClassStudent, Int> {
}