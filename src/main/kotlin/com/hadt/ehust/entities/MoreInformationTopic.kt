package com.hadt.ehust.entities

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.hadt.ehust.model.ProgressStatus
import com.hadt.ehust.model.StatusTask
import com.hadt.ehust.model.TypeSubject
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
        var nameTeacher: String? = null,
        @Column(name = "id_teacher")
        val idTeacher: Int,
        @Column(name = "email_teacher")
        val emailTeacher: String,
        @Transient
        var nameStudent: String? = null,
        @Column(name = "id_student")
        val idStudent: Int,
        @Column(name = "email_student")
        val emailStudent: String,
        @Column(name = "process_score")
        val processScore: Float? = null,
        @Column(name = "end_score")
        val endScore: Float? = null,

        @Enumerated(EnumType.ORDINAL)
        val stateProcess: ProgressStatus? = null,

        @Transient
        var type: TypeSubject? = null
)

fun MoreInformationTopic.copy(
        id: Int = this.id,
        title: String = this.title,
        description: String? = this.description,
        startTimeProgress: LocalDate? = this.startTimeProgress,
        dueTime: LocalDate? = this.dueTime,
        nameTeacher: String? = this.nameTeacher,
        idTeacher: Int = this.idTeacher,
        emailTeacher: String = this.emailTeacher,
        nameStudent: String? = this.nameStudent,
        idStudent: Int = this.idStudent,
        emailStudent: String = this.emailStudent,
        processScore: Float? = this.processScore,
        endScore: Float? = this.endScore,
        stateProcess: ProgressStatus? = this.stateProcess,
        type: TypeSubject? = this.type
) = MoreInformationTopic(
        id,
        title,
        description,
        startTimeProgress,
        dueTime,
        nameTeacher,
        idTeacher,
        emailTeacher,
        nameStudent,
        idStudent,
        emailStudent,
        processScore,
        endScore,
        stateProcess,
        type
)

