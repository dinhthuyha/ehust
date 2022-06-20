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
    property = "code_class"
)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
@JsonInclude(JsonInclude.Include.NON_NULL)
class ClassStudent (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "code_class")
    val codeClass: Int? = null,

    @Column(name = "name_teacher")
    var nameTeacher:String? = null,

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
    val semester: Int? = null,

    @ManyToMany(mappedBy = "likedClasses")
    val likes: Set<User>? = null,


    @ManyToOne
    @JoinColumn(name = "id_subject")
    val subjectClass: Subject? = null
)

fun ClassStudent.copy(
    codeClass: Int? = this.codeClass,
    nameTeacher:String? = this.nameTeacher,
    startTime: LocalTime? = this.startTime,
    finishTime: LocalTime? = this.finishTime,
    dateStudy:String? = this.dateStudy,
    dateFinishCourse: LocalDate? = this.dateFinishCourse,
    dateStartCourse: LocalDate? = this.dateStartCourse,
    studyForm: String? = this.studyForm,
    semester: Int? = this.semester,
    likes: Set<User>? = this.likes,
    subjectClass: Subject? = this.subjectClass
) = ClassStudent(
    codeClass,
    nameTeacher,
    startTime,
    finishTime,
    dateStudy,
    dateFinishCourse,
    dateStartCourse,
    studyForm,
    semester,
    likes,
    subjectClass
)