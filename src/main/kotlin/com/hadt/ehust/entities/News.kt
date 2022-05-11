package com.hadt.ehust.entities

import javax.persistence.*

@Entity
@Table(name = "news")
data class News(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int,
    val title: String,
    @Column(columnDefinition = "TEXT")
    val content: String,
    @Column(name = "date_post")
    val datePost: String
)