package com.hadt.ehust.entities

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import javax.persistence.*

@Entity
@Table(name = "pairing_teacher_with_student")
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator::class,
    property = "id"
)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
class PairingTeacherWithStudent(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column(name = "id_student")
    val idStudent : Int,

    @Column(name = "id_teacher")
    val idTeacher : Int,

    @Column(name = "name_project")
    val nameProject: String,

    val semester: Int? = null,
    @Transient
    var nameStudent: String? = null,

    @Transient
    var codeProject: String? = null

    //todo: can co id_class
)
