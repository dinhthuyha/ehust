package com.hadt.ehust.model

enum class StatusTopic {
     REQUEST,REQUESTING, ACCEPT;

    companion object {
        const val REQUEST = "REQUEST"
        const val REQUESTING = "REQUESTING"
        const val ACCEPT = "ACCEPT"

    }
}