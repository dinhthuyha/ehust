package com.hadt.ehust.entities

import com.fasterxml.jackson.annotation.*
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
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
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
@JsonInclude(JsonInclude.Include.NON_NULL)
class ClassStudent (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "code_class")
    val codeClass: Int,

    @Column(name = "code_course")
    val codeCourse: String? = null,

    @Column(name = "name_course")
    val nameCourse:String? = null,

    @Column(name = "name_teacher")
    val nameTeacher:String? = null,

    @Column(name = "is_project_subject")
    var isProjectSubject: Boolean? = null,

    val semester: Int? = null,

    @Column(name = "start_time")
   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    val startTime: LocalTime? = null,

    @Column(name = "finish_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    val finishTime: LocalTime? = null,

    @Column(name = "date_study")
    val dateStudy:String? = null,

    @Column(name = "date_finish_course")
    val dateFinishCourse: LocalDate? = null,

    @Column(name = "date_start_course")
    val dateStartCourse: LocalDate? = null,

    @Column(name = "study_form")
    val studyForm: String? = null,

    @ManyToMany(mappedBy = "likedClasses")
    @JsonIgnore
    val likes: Set<User>? = null,


    @OneToMany(mappedBy = "mClass")
    val tasks: Set<Task>? = null
)