package com.hadt.ehust.model

enum class StatusNotification {

    STATUS_UNREAD, STATUS_READ;

    companion object {
        const val UNREAD = "UNREAD"
        const val READ = "READ"
    }
}