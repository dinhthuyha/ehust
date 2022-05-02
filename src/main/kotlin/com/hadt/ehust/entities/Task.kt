package com.hadt.ehust.entities

import java.util.*
import javax.persistence.*
import javax.persistence.CascadeType;

@Entity
@Table(name = "task")
data class Task(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_task")
    val id: Int,

    @Column(name = "id_user_post")
    val userIdPost: Int,

    val description:String,

    @Column(name = "estimate_time")
    val estimateTime:Int,

    @Column(name = "spend_time")
    val spendTime: Int,

    @Column(name = "start_date")
    val startDate:Date,

    @Column(name = "due_date")
    val dueDate:Date,

    @ManyToOne
    @JoinColumn(name = "code_class")
    val subject: Subject,

    @OneToMany(mappedBy = "task")
    val commentsTask: Set<Comments>

)
