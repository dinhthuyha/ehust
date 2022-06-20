package com.hadt.ehust.model

enum class StatusTask {
    NEW, IN_PROGRESS, FINISHED, DONE, CANCEL;
    companion object {
        const val ADMIN = "ADMIN"
        const val TEACHER = "TEACHER"
        const val STUDENT = "STUDENT"
        const val UNKNOWN = "UNKNOWN"
    }
}