package com.hadt.ehust.entities

import javax.persistence.*

@Entity
@Table(name = "pairing_teacher_with_student")
data class PairingTeacherWithStudent(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int,
    @OneToOne(cascade=[CascadeType.ALL])
    @JoinColumn(name = "id_student")
    val idStudent : User,

    @OneToOne(cascade=[CascadeType.ALL])
@JoinColumn(name = "id_teacher")
val idTeacher : User
)
