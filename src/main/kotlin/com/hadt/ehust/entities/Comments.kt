package com.hadt.ehust.entities

import com.fasterxml.jackson.annotation.*
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import javax.persistence.*

@Entity
@Table(name = "comments")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
@JsonInclude(JsonInclude.Include.NON_NULL)
class Comments(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int? = null,
    @ManyToOne
    @JoinColumn(name ="id_task" )
    @JsonIgnore
    val task:Task? = null,

    var idUser: Int,
    val content: String,

    @Transient
    var nameUserPost: String? = null
)