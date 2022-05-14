package com.hadt.ehust.repository

import com.hadt.ehust.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository: JpaRepository<User,Int>{
  //  @Query(value = "select * from User u where u.email_address = ?1", nativeQuery = true)
    @Query(value = "SELECT * FROM user  u WHERE u.grade = :grade", nativeQuery = true)
    fun getListStudentInClass(@Param("grade") grade: String?): Optional<List<User?>?>

    @Query(value = "SELECT * FROM user u WHERE u.fullName =:fullName",  nativeQuery = true)
    fun findUserByFullName(@Param("full_name") fullName:String): Optional<User>

}