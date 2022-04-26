package com.hadt.ehust.entities

import javax.persistence.*

@Entity
@Table(name = "news")
data class News(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int,
    val title: String,
    val content: String
)