package com.hadt.ehust.model

import com.fasterxml.jackson.annotation.JsonValue

enum class StatusTask {
    NEW, IN_PROGRESS, FINISHED, CANCEL;

    @JsonValue
    fun toJson() = when(this) {
        NEW -> "New"
        IN_PROGRESS -> "In progress"
        FINISHED -> "Finished"
        CANCEL -> "Cancelled"
    }

}