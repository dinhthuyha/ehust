package com.hadt.ehust.entities

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "user")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int,
    @Column(name = "password")
    val passWord: String,
    @Column(name = "full_name")
    val fullName: String,
    @Column(name = "íntitute_of_management")
    val instituteOfManagement: String,
    val gender: String,
    val grade: String,
    val course: Int,


    val address: String,
    @Column(name = "image_background")
    val imageBackground: String,
    @Column(name = "image_avata")
    val imageAvata: String,
    val birthday: Date,
    val email: String,
    val roleId: String,
    @Column(name = "cadre_status")
    val cadreStatus: String,
    @Column(name = "unit")
    val unit:String,

    @ManyToMany
    @JoinTable(name = "user_class", joinColumns = [JoinColumn(name = "id_user")], inverseJoinColumns = [JoinColumn(name = "id_class")] )
    val likedClasses: Set<Class>,

    @ManyToMany
    @JoinTable(name = "user_task", joinColumns = [JoinColumn(name = "id_user")], inverseJoinColumns = [JoinColumn(name = "id_task")] )
    val likedTasks: Set<Task>,

    @OneToMany(mappedBy = "user")
     val comments: Set<Comments>,

    @OneToOne(mappedBy = "idStudent")
    val pairingStudent: PairingTeacherWithStudent,
    @OneToOne(mappedBy = "idTeacher")
    val pairingTeacher: PairingTeacherWithStudent,


    )