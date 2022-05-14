package com.hadt.ehust.entities

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "task")
data class Task(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_task")
    @JsonProperty("id_task")
    val id: Int,

    @Column(name = "id_user_collaborator")
    @JsonProperty("id_user_collaborator")
    val userIdPost: Int,

    val description: String,

    @Column(name = "estimate_time")
    @JsonProperty("estimate_time")
    val estimateTime: Int,

    @Column(name = "spend_time")
    @JsonProperty("spend_time")
    val spendTime: Int,

    @Column(name = "start_date")
    @JsonProperty("start_date")
    val startDate: Date,

    @Column(name = "due_date")
    @JsonProperty("due_date")
    val dueDate: Date,

    @ManyToOne
    @JoinColumn(name = "code_class")
    @JsonProperty("code_class")
    val mClass: ClassStudent,

    @OneToMany(mappedBy = "task")
    val commentsTask: Set<Comments>,


    @ManyToMany(mappedBy = "likedTasks")
    val likes: Set<User>,
)
