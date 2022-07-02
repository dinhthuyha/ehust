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
    val id: Int? = null,

    val title: String? = null,

    @Enumerated(EnumType.ORDINAL)
    val status: StatusTask? = null,
    val description: String? = null,

    @Column(name = "estimate_time")
    val estimateTime: Int? = null,

    @Column(name = "spend_time")
    val spendTime: Int? = null,

    @Column(name = "start_date")
    val startDate: LocalDate? = null,

    @Column(name = "due_date")
    val dueDate: LocalDate? = null,

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

fun Task.copy(
     id: Int? = this.id,
     title: String? = this.title,
     status: StatusTask? = this.status,
     description: String? = this.description,
     estimateTime: Int? = this.estimateTime,
     spendTime: Int? = this.spendTime,
     startDate: LocalDate? = this.startDate,
     dueDate: LocalDate? = this.dueDate,
     progress: Float? = this.progress,
     assignee: String? = this.assignee,
     topics: Topic? = this.topics,
     commentsTask: Set<Comments>? = this.commentsTask,
     likes: Set<User>? = this.likes,
) = Task(
    id,
    title,
    status,
    description,
    estimateTime,
    spendTime,
    startDate,
    dueDate,
    progress,
    assignee,
    topics,
    commentsTask,
    likes
)
