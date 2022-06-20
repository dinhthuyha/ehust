package com.hadt.ehust.entities

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.ObjectIdGenerators
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
data class Task(
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

    @ManyToOne
    @JoinColumn(name = "id_topic")
    val topics: Topic? = null,

    @OneToMany(mappedBy = "task")
    val commentsTask: Set<Comments>? = null,


    @ManyToMany(mappedBy = "likedTasks")
    val likes: Set<User>? = null,
)
