package com.hadt.ehust.repository

import com.hadt.ehust.entities.PairingTeacherWithStudent
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*
import kotlin.collections.List

@Repository
interface PairingRepository : JpaRepository<PairingTeacherWithStudent, Int>{

    @Query(value = "SELECT * FROM pairing_teacher_with_student u WHERE u.id_student =:id and u.name_project =:name", nativeQuery = true)
    fun findUserByIdStudentAndNameProject(@Param("id") id:Int, @Param("name") name: String?): Optional<PairingTeacherWithStudent?>

    fun findByNameProject(nameProject: String): List<PairingTeacherWithStudent>?
}