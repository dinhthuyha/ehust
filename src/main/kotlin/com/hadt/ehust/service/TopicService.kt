package com.hadt.ehust.service

import com.hadt.ehust.entities.Subject
import com.hadt.ehust.entities.Topic
import com.hadt.ehust.entities.copy
import com.hadt.ehust.model.Role
import com.hadt.ehust.model.StatusTopic
import com.hadt.ehust.repository.TopicRepository
import com.hadt.ehust.repository.UserRepository
import com.hadt.ehust.utils.Utils
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class TopicService(
    private val topicRepository: TopicRepository,
    private val userRepository: UserRepository
) {

    fun updateTopicStatus(idTopic: Int, status: StatusTopic, idStudent: Int): ResponseEntity<Unit> {

        topicRepository.findById(idTopic).map {
            if (idStudent != 0) {
                it.copy(status = status).let {
                    topicRepository.save(it)
                }
            } else {
                it.copy(status = status, idStudent = idStudent).let {
                    topicRepository.save(it)
                }
            }

        }
        return ResponseEntity<Unit>(HttpStatus.OK)
    }

    fun findTopicByNameProjectAndIdTeacher(nameTeacher: String, idProject: String, idTeacher: Int): ResponseEntity<List<Topic>>? {
        var mIdTeacher: Int? = idTeacher
        when(Utils.hasRole(Role.ROLE_TEACHER)){
            true -> {}
            false -> {
                mIdTeacher = userRepository.findByFullName(nameTeacher)?.id
            }
        }
        mIdTeacher?.let {
            val topics = topicRepository.findByIdTeacher(it)?.filter { it.subject?.id == idProject }
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
            return ResponseEntity.ok().body(topics)
        }

        return ResponseEntity.notFound().build()

    }
}