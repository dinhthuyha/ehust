package com.hadt.ehust.response

class ProjectResponse(
    val codeClass: Int,
    val codeCourse: String,
    val nameCourse:String,
    val semester: Int,
    val nameTeacher: String?=null,
    val studyForm: String
)