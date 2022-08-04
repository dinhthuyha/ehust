package com.hadt.ehust.entities

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.hadt.ehust.model.ProgressStatus
import com.hadt.ehust.model.StatusTopic
import org.apache.tomcat.jni.Local
import java.time.LocalTime
import javax.persistence.*

@Entity
@Table(name = "topic")
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator::class,
    property = "id"
)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
@JsonInclude(JsonInclude.Include.NON_NULL)
class Topic(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int? = null,
    val name: String,
    @Column(name = "id_student")
    val idStudent: Int? = null,

    @Transient
    val nameStudent: String? = null,

    @Column(name = "id_teacher")
    val idTeacher: Int? = null,

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "progress_status")
    val progressStatus: ProgressStatus?= null,

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status_topic")
    val status: StatusTopic? = null,

    @ManyToOne
    @JoinColumn(name = "id_subject")
    val subject: Subject? = null,

    @OneToMany(mappedBy = "topics")
    val listClass: Set<Task>? = null,

    val semester: Int? = null

    )

fun Topic.copy(
    id: Int? = this.id,
    name: String = this.name,
    idStudent: Int? = this.idStudent,
    idTeacher: Int? = this.idTeacher,
    status: StatusTopic? = this.status,
    subject: Subject? = this.subject,
    nameStudent: String? = "",
    progressStatus: ProgressStatus? = this.progressStatus,
    semester: Int? = this.semester

) = Topic(
    id = id,
    name = name,
    idStudent = idStudent,
    idTeacher = idTeacher,
    status = status,
    subject = subject,
    nameStudent = nameStudent,
    progressStatus = progressStatus,
    semester = semester
    )