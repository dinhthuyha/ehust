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
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int,
    @OneToOne(cascade=[CascadeType.ALL])
    @JoinColumn(name = "id_student")
    @JsonIgnore
    val idStudent : User,

    @OneToOne(cascade=[CascadeType.ALL])
    @JoinColumn(name = "id_teacher")

    val idTeacher : User

    //todo: can co id_class
)
