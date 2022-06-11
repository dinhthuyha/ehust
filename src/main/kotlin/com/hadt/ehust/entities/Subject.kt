package com.hadt.ehust.entities

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import javax.persistence.*

@Entity
@Table(name = "subject")
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator::class,
    property = "id"
)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
@JsonInclude(JsonInclude.Include.NON_NULL)
class Subject(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int,
    val name: String,
    @Column(name = " is_project")
    val isProject: Boolean = false,

    @OneToMany(mappedBy = "subject")
    val topics: Set<Topic>? = null,

    @OneToMany(mappedBy = "subjectClass")
    val listClass: Set<ClassStudent>? = null,

    @ManyToOne
    @JoinColumn(name = "id")
    val userSubject: User? = null

    )