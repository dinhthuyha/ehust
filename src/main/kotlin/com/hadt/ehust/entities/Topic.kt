package com.hadt.ehust.entities

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.hadt.ehust.model.StatusTopic
import javax.persistence.*

@Entity
@Table(name = "topic")
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator::class,
    property = "id"
)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Topic(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int,
    val name: String,
    val idStudent: Int? = null,
    val idTeacher: Int? = null,

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status_topic")
    val status: StatusTopic? = null,

    @ManyToOne
    @JoinColumn(name = "id_subject")
    val subject: Subject? = null
)