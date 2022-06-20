package com.hadt.ehust.entities

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import javax.persistence.*

@Entity
@Table(name = "pairing_teacher_with_student")
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator::class,
    property = "id"
)
class PairingTeacherWithStudent(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column(name = "id_student")
    val idStudent : Int,

    @Column(name = "id_teacher")
    val idTeacher : Int,

    @Column(name = "name_project")
    val nameProject: String

    //todo: can co id_class
)
