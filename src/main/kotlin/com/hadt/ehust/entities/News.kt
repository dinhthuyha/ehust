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
    val id: Int? = null,
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
    //post nay do ai cap nhat
    val idUserPost: Int? = null,
    @Transient
    val nameUserPost: String?  = null,
    val idTask: Int? = null
) {
    fun copy(
        id: Int? = this.id,
        title: String = this.title,
        content: String = this.content,
        datePost: String = this.datePost,
        type: TypeNotification = this.type,
        status: StatusNotification = this.status,
        idUserPost: Int? = this.idUserPost,
        nameUserPost: String? = this.nameUserPost,
        idTask: Int? = this.idTask
    ): News = News(id, title, content, datePost, type, status, idUserPost, nameUserPost, idTask)
}