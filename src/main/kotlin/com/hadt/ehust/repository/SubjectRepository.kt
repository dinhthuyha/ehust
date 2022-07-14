package com.hadt.ehust.repository

import com.hadt.ehust.entities.Subject
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SubjectRepository: JpaRepository<Subject, String> {

    @Query(value = "SELECT * FROM subject c WHERE c.name = :nameCourse and  c.is_project =true", nativeQuery = true)
    fun findAllUserInClass(  @Param("nameCourse") nameCourse: String): Optional<Subject>

    fun findByName(name: String): Subject?
}