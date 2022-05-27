package com.hadt.ehust.entities

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import org.springframework.format.annotation.DateTimeFormat
import java.sql.Time
import java.time.LocalDate
import java.time.LocalTime
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

    @Column(name = "name_teacher")
    val nameTeacher:String,

    @Column(name = "is_project_subject")
    var isProjectSubject: Boolean,

    val semester: Int,

    @Column(name = "start_time")
    @DateTimeFormat(pattern = "HH:mm")
    val startTime: LocalTime,

    @Column(name = "finish_time")
    @DateTimeFormat(pattern = "HH:mm")
    val finishTime: LocalTime,

    @Column(name = "date_study")
    val dateStudy:String,

    @Column(name = "date_finish_course")
    val dateFinishCourse: LocalDate,

    @Column(name = "date_start_course")
    val dateStartCourse: LocalDate,

    @Column(name = "study_form")
    val studyForm: String,

    @ManyToMany(mappedBy = "likedClasses")
    @JsonIgnore
    val likes: Set<User>,


    @OneToMany(mappedBy = "mClass")
    val tasks: Set<Task>
)