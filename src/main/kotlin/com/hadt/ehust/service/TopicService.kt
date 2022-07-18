package com.hadt.ehust.service

import com.hadt.ehust.entities.Subject
import com.hadt.ehust.entities.Topic
import com.hadt.ehust.entities.copy
import com.hadt.ehust.model.Role
import com.hadt.ehust.model.StatusTopic
import com.hadt.ehust.repository.PairingRepository
import com.hadt.ehust.repository.SubjectRepository
import com.hadt.ehust.repository.TopicRepository
import com.hadt.ehust.repository.UserRepository
import com.hadt.ehust.utils.Utils
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class TopicService(
    private val topicRepository: TopicRepository,
    private val userRepository: UserRepository,
    private val subjectRepository: SubjectRepository,
    private val pairingRepository: PairingRepository
) {

    fun updateTopicStatus(idTopic: Int, status: StatusTopic, idStudent: Int): ResponseEntity<HttpStatus> {

        topicRepository.findById(idTopic).map {
            if (idStudent == 0) {
                it.copy(status = status).let {
                    topicRepository.save(it)
                }
            } else {
                it.copy(status = status, idStudent = idStudent).let {
                    topicRepository.save(it)
                }
            }

        }
        return ResponseEntity.ok(HttpStatus.OK)
    }

    fun findTopicByNameProjectAndIdTeacher(
        nameTeacher: String,
        idProject: String,
        idTeacher: Int,
        semester: Int
    ): ResponseEntity<MutableList<Topic?>> {
        var mIdTeacher: Int? = idTeacher
        when (Utils.hasRole(Role.ROLE_TEACHER)) {
            true -> {}
            false -> {
                mIdTeacher = userRepository.findByFullName(nameTeacher)?.id
            }
        }
        mIdTeacher?.let {
            var topics =
                topicRepository.findByIdTeacher(it)?.filter { it.subject?.id == idProject && it.semester == semester }
                    ?.map {

                        if (Utils.hasRole(Role.ROLE_TEACHER)) {
                            val nameStudent =
                                it.idStudent?.let { it1 -> userRepository.findById(it1) }?.map { it.fullName }?.get()
                            it.copy(
                                subject = Subject(it.subject?.id.toString(), it.subject?.name.toString()),
                                nameStudent = nameStudent ?: ""
                            )
                        } else {
                            it.copy(
                                subject = Subject(it.subject?.id.toString(), it.subject?.name.toString()),
                            )
                        }

                    }
            val result = mutableListOf<Topic?>()
            topics?.forEach { topic ->
                val pair = pairingRepository.findByIdTeacher(topic?.idTeacher ?: 0)
                    ?.firstOrNull { it.semester == semester && it.idStudent == topic?.idStudent }
                if (pair!= null) {
                    result.add(topic)
                }
            }
            return ResponseEntity.ok().body(result)
        }

        return ResponseEntity.notFound().build()

    }

    fun addTopicSuggestion(topicName: String?, nameTeacher: String?, idSubject: String): ResponseEntity<*> {
        val subject = subjectRepository.findByIdOrNull(idSubject) ?: throw NotFoundException()
        val teacher = userRepository.findByFullName(nameTeacher ?: "") ?: throw NotFoundException()
        val studentId = Utils.getCurrentUserId()
        val topic = Topic(
            idStudent = studentId,
            name = topicName ?: "",
            idTeacher = teacher.id,
            subject = subject,
            status = StatusTopic.REQUESTING
        )
        topicRepository.save(topic)
        return ResponseEntity.ok(null)
    }
}