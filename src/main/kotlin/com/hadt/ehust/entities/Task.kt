package com.hadt.ehust.entities

import com.fasterxml.jackson.annotation.*
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.hadt.ehust.model.StatusTask
import java.time.LocalDate
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "task")
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator::class,
    property = "id"
)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
@JsonInclude(JsonInclude.Include.NON_NULL)
class Task(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    val id: Int,

    val title: String,

    @Enumerated(EnumType.ORDINAL)
    val status: StatusTask,
    val description: String? = null,

    @Column(name = "estimate_time")
    val estimateTime: Int? = null,

    @Column(name = "spend_time")
    val spendTime: Int? = null,

    @Column(name = "start_date")
    val startDate: LocalDate,

    @Column(name = "due_date")
    val dueDate: LocalDate,

    val progress: Float? = 0f,
    val assignee: String? = null,

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_topic")
    val topics: Topic? = null,

    @JsonIgnore
    @OneToMany(mappedBy = "task")
    val commentsTask: Set<Comments>? = null,

    @JsonIgnore
    @ManyToMany(mappedBy = "likedTasks")
    val likes: Set<User>? = null,
)
