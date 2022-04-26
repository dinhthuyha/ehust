package com.hadt.ehust.entities

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "user")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int,
    @Column(name = "full_name")
    val fullName: String,
    @Column(name = "íntitute_of_management")
    val íntituteOfManagement: String,
    val gender: String,
    val grade: String,
    val course: Int,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "code_class")
    val codeClass: Subject,
    val address: String,
    @Column(name = "image_background")
    val imageBackground: String,
    @Column(name = "image_avata")
    val imageAvata: String,
    val birthday: Date,
    val email: String,
    val roleId: String,

    @OneToOne(mappedBy = "userIdPost")
    val task: Task,
    @OneToOne(mappedBy = "idUser")
    val comments: Comments,
    @OneToOne(mappedBy = "idStudent")
    val pairingStudent: PairingTeacherWithStudent,
    @OneToOne(mappedBy = "idTeacher")
    val pairingTeacher: PairingTeacherWithStudent,


    )