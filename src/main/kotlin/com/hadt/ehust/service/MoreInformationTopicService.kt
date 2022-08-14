package com.hadt.ehust.service

import com.hadt.ehust.entities.MoreInformationTopic
import com.hadt.ehust.entities.copy
import com.hadt.ehust.model.ProgressStatus
import com.hadt.ehust.model.TypeSubject
import com.hadt.ehust.repository.MoreInformationTopicRepository
import com.hadt.ehust.repository.TopicRepository
import com.hadt.ehust.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class MoreInformationTopicService(
        private val moreInformationTopicRepository: MoreInformationTopicRepository,
        private val userRepository: UserRepository,
        private val topicRepository: TopicRepository
) {

    fun findById(id: Int): ResponseEntity<MoreInformationTopic> {
        var semester: Int? = 0
        topicRepository.findById(id).map { semester = it.semester }
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
            it.semester = semester
            ResponseEntity.ok().body(it)
        }
                .orElse(ResponseEntity.notFound().build())
    }

    fun updateStateProcessInformationTopic(topic: MoreInformationTopic): ResponseEntity<HttpStatus> {
        topicRepository.findById(topic.id).map { it.copy(progressStatus = topic.stateProcess).let { topicRepository.save(it) } }
       // moreInformationTopicRepository.findById(topic.id).map { it.copy(stateProcess = topic.stateProcess).let { moreInformationTopicRepository.save(it) } }
          return  moreInformationTopicRepository.findById(topic.id).map {

                if (topic.processScore!=null){
                    it.copy(processScore = topic.processScore, endScore = topic.endScore).let {  moreInformationTopicRepository.save(it) }
                }
                if (topic.stateProcess!=null){
                   it.copy(stateProcess = topic.stateProcess).let {  moreInformationTopicRepository.save(it) }
                }

                ResponseEntity.ok().body(HttpStatus.OK)
            }.orElse(ResponseEntity.notFound().build())

    }
}