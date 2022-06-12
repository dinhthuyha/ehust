package com.hadt.ehust.repository

import com.hadt.ehust.entities.PairingTeacherWithStudent
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PairingRepository : JpaRepository<PairingTeacherWithStudent, Int>{
}