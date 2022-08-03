package com.hadt.ehust.entities

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.hadt.ehust.model.ProgressStatus
import java.time.LocalDate
import java.time.LocalTime
import javax.persistence.*

@Entity
@Table(name = "more_information_topic")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
@JsonInclude(JsonInclude.Include.NON_NULL)
class MoreInformationTopic(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int,
    val title: String,
    val description: String? = null,
    @Column(name = "start_time_progress")
    val startTimeProgress: LocalDate? = null,
    @Column(name = "due_time")
    val dueTime: LocalDate? = null,
    @Transient
    var nameTeacher: String,
    @Column(name = "id_teacher")
    val idTeacher: Int,
    @Column(name = "email_teacher")
    val emailTeacher: String,
    @Transient
    var nameStudent: String,
    @Column(name = "id_student")
    val idStudent: Int,
    @Column(name ="email_student")
    val emailStudent: String,
    @Column(name = "process_score")
    val processScore: Float? = null,
    @Column(name = "end_score")
    val endScore: Float? = null,
    val stateProcess: ProgressStatus? = null
)

