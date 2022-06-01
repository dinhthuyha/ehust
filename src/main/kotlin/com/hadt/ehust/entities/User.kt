package com.hadt.ehust.entities

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.hadt.ehust.model.Role
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "user")
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator::class,
    property = "id"
)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int,
    @Column(name = "password")
    @JsonIgnore
    val password: String?=null,
    @Column(name = "full_name")
    val fullName: String,
    @Column(name = "institute_of_management")
    val instituteOfManagement: String,
    val gender: String,
    val grade: String,
    val course: String,


    val address: String?=null,
    @Column(name = "image_background")
    val imageBackground: String,
    @Column(name = "image_avatar")
    val imageAvatar: String,
    val birthday: Date?=null,
    val email: String,

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "role_id")
    val role: Role,

    @Column(name = "cadre_status")
    val cadreStatus: String,
    @Column(name = "unit")
    val unit:String,

    @ManyToMany
    @JoinTable(name = "user_class", joinColumns = [JoinColumn(name = "id_user")], inverseJoinColumns = [JoinColumn(name = "id_class")] )
    val likedClasses: Set<ClassStudent>?=null,

    @ManyToMany
    @JoinTable(name = "user_task", joinColumns = [JoinColumn(name = "id_user")], inverseJoinColumns = [JoinColumn(name = "id_task")] )
    val likedTasks: Set<Task>?=null,

    @OneToMany(mappedBy = "user")
    val comments: Set<Comments>?=null,

    @OneToOne(mappedBy = "idStudent")
    val pairingStudent: PairingTeacherWithStudent?=null,
    @OneToOne(mappedBy = "idTeacher")
    val pairingTeacher: PairingTeacherWithStudent?= null,


    )