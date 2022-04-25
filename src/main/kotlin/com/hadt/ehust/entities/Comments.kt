package com.hadt.ehust.entities

import javax.persistence.*

@Entity
data class Comments(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "id_task")
    val idTask: Task,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "id_user")
    val idUser: User
)