package com.hadt.ehust.response

import java.time.LocalDate
import java.time.LocalTime

data class ScheduleModel(
    val startTime: LocalTime,
    val finishTime: LocalTime,
    val dateStudy : String,
    val dueDateStudy: LocalDate
)