package com.hadt.ehust.repository

import com.hadt.ehust.entities.ClassStudent
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface ClassStudentRepository : JpaRepository<ClassStudent, Int> {

    @Query(value = "SELECT * FROM class c WHERE c.name_course = :nameCourse and c.semester = :semester and c.is_project_subject =true", nativeQuery = true)
    fun findAllUserInClass(@Param("semester") semester: Int, @Param("nameCourse") nameCourse: String): Optional<List<ClassStudent>>

}