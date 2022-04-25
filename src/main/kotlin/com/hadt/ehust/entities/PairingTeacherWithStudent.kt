package com.hadt.ehust.entities

import javax.persistence.*

@Entity
data class PairingTeacherWithStudent(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int,
    @Column(name = "id_student")
    val idStudent: Int,
    @Column(name = "id_teacher")
    val isTeacher: Int
)
