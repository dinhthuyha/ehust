package com.hadt.ehust.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import javax.persistence.*

@Entity
@Table(name = "attachments")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
@JsonInclude(JsonInclude.Include.NON_NULL)
class Attachment(
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null,
    @ManyToOne
    @JoinColumn(name = "id_task")
    @JsonIgnore
    var task: Task? = null,
    var filename: String?,
    var filePath: String?,
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_comment")
    var comment: Comments? = null
)