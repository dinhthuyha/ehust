package com.hadt.ehust.response

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.Column

class ProfileResponse (

    val id: Int,
    @JsonProperty("full_name")
    val fullName: String,
//    val instituteOfManagement: String,
//    val gender: String,
//    val grade: String,
//    val course: String,
//    val imageBackground: String,
//    val imageAvatar: String,
//    val email: String,
//    val cadreStatus: String,
//    val unit:String
        ){
}