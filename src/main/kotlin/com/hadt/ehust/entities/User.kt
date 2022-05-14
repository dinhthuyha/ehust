package com.hadt.ehust.entities

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "user")
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator::class,
    property = "id"
)
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int,
    @Column(name = "password")
    @JsonProperty("password")
    val passWord: String,
    @Column(name = "full_name")
    @JsonProperty("full_name")
    val fullName: String,
    @Column(name = "institute_of_management")
    @JsonProperty("institute_of_management")
    val instituteOfManagement: String,
    val gender: String,
    val grade: String,
    val course: String,


    val address: String,
    @Column(name = "image_background")
    @JsonProperty("image_background")
    val imageBackground: String,
    @Column(name = "image_avatar")
    @JsonProperty("image_avatar")
    val imageAvatar: String,
    val birthday: Date,
    val email: String,
    val roleId: Int,
    @Column(name = "cadre_status")
    @JsonProperty("cadre_status")
    val cadreStatus: String,
    @Column(name = "unit")
    val unit:String,

    @ManyToMany
    @JoinTable(name = "user_class", joinColumns = [JoinColumn(name = "id_user")], inverseJoinColumns = [JoinColumn(name = "id_class")] )
    val likedClasses: Set<ClassStudent>,

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