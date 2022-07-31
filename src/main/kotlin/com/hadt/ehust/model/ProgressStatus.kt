package com.hadt.ehust.model

import com.fasterxml.jackson.annotation.JsonValue

enum class ProgressStatus {
    RESPONDING, DONE,UNFINISHED;

    @JsonValue
    fun toJson() = when(this) {
        RESPONDING -> "Đang thực hiện"
        DONE -> "Đã hoàn thành"
        UNFINISHED -> "Chưa hoàn thành"
    }
}