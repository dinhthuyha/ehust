package com.hadt.ehust.response

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate
import java.time.LocalTime

data class ScheduleModel(
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    val startTime: LocalTime,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    val finishTime: LocalTime,
    val dateStudy : String,
    val dueDateStudy: LocalDate,
    val startDateStudy: LocalDate,
    val courseName: String
)