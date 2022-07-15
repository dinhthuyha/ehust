package com.hadt.ehust.entities

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.hadt.ehust.model.StatusNotification
import com.hadt.ehust.model.TypeNotification
import javax.persistence.*

@Entity
@Table(name = "news")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
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
    val status: StatusNotification,
    val idUserPost: Int? = null,
    @Transient
    val nameUserPost: String?  = null
) {
    fun copy(
        id: Int = this.id,
        title: String = this.title,
        content: String = this.content,
        datePost: String = this.datePost,
        type: TypeNotification = this.type,
        status: StatusNotification = this.status,
        idUserPost: Int? = this.idUserPost,
        nameUserPost: String? = this.nameUserPost
    ): News = News(id, title, content, datePost, type, status, idUserPost, nameUserPost)
}