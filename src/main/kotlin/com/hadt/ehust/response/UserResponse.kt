package com.hadt.ehust.response

class UserResponse(
    val id: Int,
    val fullName: String,
    val instituteOfManagement: String,
    val gender: String,
    val grade: String,
    val course: String,
    val email: String,
    val cadreStatus: String?=null,
    val unit:String?=null,
    val roleId: Int,
    val imageBackground: String,
    val imageAvatar: String,
)