package com.hadt.ehust.entities

import com.fasterxml.jackson.annotation.*
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.security.Timestamp
import java.time.LocalDateTime
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
    var task:Task? = null,

    var idUser: Int,
    val content: String,

    @OneToMany(mappedBy = "comment")
    var attachments: Set<Attachment>? = null,

    @Column(name = "timestamp", columnDefinition = "TIMESTAMP")
    val timestamp: LocalDateTime? = null,

    @Transient
    var nameUserPost: String? = null
)