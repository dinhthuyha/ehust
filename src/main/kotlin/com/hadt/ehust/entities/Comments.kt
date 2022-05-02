package com.hadt.ehust.entities

import javax.persistence.*

@Entity
@Table(name = "comments")
data class Comments(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int,
    @ManyToOne
    @JoinColumn(name ="id_task" )
    val task:Task,

    @ManyToOne
    @JoinColumn(name = "id_user")
    val user: User,
    val content: String
)