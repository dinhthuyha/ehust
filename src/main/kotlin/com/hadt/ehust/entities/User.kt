package com.hadt.ehust.entities

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
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
@JsonInclude(JsonInclude.Include.NON_NULL)
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
    val instituteOfManagement: String? = null,
    val gender: String? = null,
    val grade: String? = null,
    val course: String? = null,


    @Column(name = "image_background")
    val imageBackground: String? = null,
    @Column(name = "image_avatar")
    val imageAvatar: String? = null,
    val email: String? = null,

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "role_id")
    val role: Role? = null,

    @Column(name = "cadre_status")
    val cadreStatus: String? = null,
    @Column(name = "unit")
    val unit:String? = null,

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