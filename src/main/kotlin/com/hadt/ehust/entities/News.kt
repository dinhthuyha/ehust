package com.hadt.ehust.entities

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*

@Entity
@Table(name = "news")
class News(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int,
    val title: String,
    @Column(columnDefinition = "TEXT")
    val content: String,
    @Column(name = "date_post")
    @JsonProperty("date_post")
    val datePost: String
) {
    fun copy(
        id: Int = this.id,
        title: String = this.title,
        content: String = this.content,
        datePost: String = this.datePost
    ): News = News(id, title, content, datePost)
}