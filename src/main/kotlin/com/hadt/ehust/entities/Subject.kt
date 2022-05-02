package com.hadt.ehust.entities

import java.sql.Time
import javax.persistence.*

@Entity
@Table(name = "subject")
data class Subject (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "code_class")
    val codeClass: Int,
    @Column(name = "code_course")
    val codeCourse: String,
    @Column(name = "name_course")
    val nameCourse:String,
    val semester: Int,
    @Column(name = "start_time")
    val startTime: Time,
    @Column(name = "finish_time")
    val finishTime: Time,
    @Column(name = "date_study")
    val dateStudy:String,
    @Column(name = "study_form")
    val studyForm: String,

    @ManyToMany(mappedBy = "likedSubjects")
    val likes: Set<User>,


    @OneToMany(mappedBy = "subject")
    val tasks: Set<Task>
)