package com.hadt.ehust.entities

import java.util.*
import javax.persistence.*
import javax.persistence.CascadeType;

@Entity
data class Task(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "id_user_post")
    val idUserPost:User,

    val description:String,
    @Column(name = "estimate_time")
    val estimateTime:Int,
    @Column(name = "spend_time")
    val spendTime: Int,
    @Column(name = "start_date")
    val startDate:Date,
    @Column(name = "due_date")
    val dueDate:Date,

    @OneToOne(mappedBy = "task")
    val comments: Comments
)
