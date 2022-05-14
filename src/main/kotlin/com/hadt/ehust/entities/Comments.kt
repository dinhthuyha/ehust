package com.hadt.ehust.entities

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*

@Entity
@Table(name = "comments")
data class Comments(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int,
    @ManyToOne
    @JoinColumn(name ="id_task" )
    @JsonProperty("id_task")
    val task:Task,

    @ManyToOne
    @JoinColumn(name = "id_user")
    @JsonProperty("id_user")
    val user: User,
    val content: String
)