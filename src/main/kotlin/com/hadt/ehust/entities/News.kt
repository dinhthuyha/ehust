package com.hadt.ehust.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class News(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int,
    val title: String,
    val content: String
)