package com.hadt.ehust.service

import com.hadt.ehust.entities.MoreInformationTopic
import com.hadt.ehust.model.TypeSubject
import com.hadt.ehust.repository.MoreInformationTopicRepository
import com.hadt.ehust.repository.TopicRepository
import com.hadt.ehust.repository.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class MoreInformationTopicService(
    private val moreInformationTopicRepository: MoreInformationTopicRepository,
    private val userRepository: UserRepository,
    private val topicRepository: TopicRepository
) {

    fun findById(id: Int): ResponseEntity<MoreInformationTopic> {

        return moreInformationTopicRepository.findById(id).map {
            var nameStudent = ""
            userRepository.findById(it.idStudent).map { nameStudent = it.fullName }
            var nameTeacher = ""
            userRepository.findById(it.idTeacher).map { nameTeacher = it.fullName }
            it.nameTeacher = nameTeacher
            it.nameStudent = nameStudent
            var typeSubject: TypeSubject? = null
            topicRepository.findById(id).map { typeSubject = it.subject?.type }
            it.type = typeSubject
            ResponseEntity.ok().body(it)
        }
            .orElse(ResponseEntity.notFound().build())
    }
}