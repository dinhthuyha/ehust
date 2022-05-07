package com.hadt.ehust.entities

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "task")
data class Task(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_task")
    val id: Int,

    @Column(name = "id_user_collaborator")
    val userIdPost: Int,

    val description: String,

    @Column(name = "estimate_time")
    val estimateTime: Int,

    @Column(name = "spend_time")
    val spendTime: Int,

    @Column(name = "start_date")
    val startDate: Date,

    @Column(name = "due_date")
    val dueDate: Date,

    @ManyToOne
    @JoinColumn(name = "code_class")
    val mClass: Class,

    @OneToMany(mappedBy = "task")
    val commentsTask: Set<Comments>,


    @ManyToMany(mappedBy = "likedTasks")
    val likes: Set<User>,
)
