package com.hadt.ehust.entities

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import javax.persistence.*

@Entity
@Table(name = "comments")
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator::class,
    property = "id"
)
data class Comments(
    @Id

    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int,
    @ManyToOne
    @JoinColumn(name ="id_task" )
    @JsonIgnore
    val task:Task,

    @ManyToOne
    @JoinColumn(name = "id_user")
    val user: User,
    val content: String
)