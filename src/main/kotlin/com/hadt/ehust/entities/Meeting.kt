package com.hadt.ehust.entities

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.time.LocalDate
import java.time.LocalTime
import javax.persistence.*

@Entity
@Table(name = "meeting")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
@JsonInclude(JsonInclude.Include.NON_NULL)
class Meeting (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int? = null,
    @Column(name = "id_user_teacher")
    val idUserTeacher: Int? = null,
    @Column(name = "id_user_student")
    val idUserStudent: Int? = null,
    val title: String,
    val date: LocalDate,
    @Column(name = "start_time")
    val startTime: LocalTime,
    @Column(name = "end_time")
    val endTime: LocalTime
        )