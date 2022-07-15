package com.hadt.ehust.entities

import com.fasterxml.jackson.annotation.JsonProperty
import com.hadt.ehust.model.StatusNotification
import com.hadt.ehust.model.TypeNotification
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
    val datePost: String,
    @Enumerated(EnumType.ORDINAL)
    val type: TypeNotification,
    @Enumerated(EnumType.ORDINAL)
    val status: StatusNotification
) {
    fun copy(
        id: Int = this.id,
        title: String = this.title,
        content: String = this.content,
        datePost: String = this.datePost,
        type: TypeNotification = this.type,
        status: StatusNotification = this.status
    ): News = News(id, title, content, datePost, type, status)
}