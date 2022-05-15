package com.hadt.ehust.entities

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import java.sql.Time
import javax.persistence.*

@Entity
@Table(name = "class")
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator::class,
    property = "codeClass"
)
class ClassStudent (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "code_class")
    val codeClass: Int,

    @Column(name = "code_course")
    val codeCourse: String,
    @Column(name = "name_course")

    val nameCourse:String,
    @Column(name = "is_project_subject")

    var isProjectSubject: Boolean,
    val semester: Int,
    @Column(name = "start_time")

    val startTime: Time,
    @Column(name = "finish_time")

    val finishTime: Time,
    @Column(name = "date_study")

    val dateStudy:String,
    @Column(name = "study_form")

    val studyForm: String,


    @ManyToMany(mappedBy = "likedClasses")
    @JsonIgnore
    val likes: Set<User>,


    @OneToMany(mappedBy = "mClass")
    val tasks: Set<Task>
)