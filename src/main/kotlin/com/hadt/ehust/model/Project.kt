package com.hadt.ehust.model

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Project(
    @JsonProperty( value = "code_class")
    val codeClass: Int? = null,
    @JsonProperty(value = "code_course")
    val codeCourse: String? = null,
    @JsonProperty(value = "name_teacher")
    var nameTeacher: String? = null,
    val name: String? = null,
    val semester: Int? = null,
)