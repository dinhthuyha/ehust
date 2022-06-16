package com.hadt.ehust.service

import com.hadt.ehust.entities.Subject
import com.hadt.ehust.entities.Topic
import com.hadt.ehust.entities.copy
import com.hadt.ehust.model.StatusTopic
import com.hadt.ehust.repository.TopicRepository
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class TopicService(private val topicRepository: TopicRepository) {

    fun updateTopicStatus(idTopic: Int, status: StatusTopic, idStudent: Int): ResponseEntity<Unit> {

        topicRepository.findById(idTopic).map{
            if (idStudent !=0){
                it.copy(status = status).let {
                    topicRepository.save(it)
                }
            }else{
                it.copy(status = status, idStudent = idStudent).let {
                    topicRepository.save(it)
                }
            }

        }
        return ResponseEntity<Unit>(HttpStatus.OK)
    }

    fun findTopicByNameProjectAndIdTeacher(idTeacher: Int, idProject: String): ResponseEntity<List<Topic>>? {
//        var topics = mutableListOf<Topic>()
        val topics = topicRepository.findByIdTeacher(idTeacher)
            ?.map {
                it.copy(subject = Subject(it.subject?.id.toString(), it.subject?.name.toString()))
            }


        return ResponseEntity.ok().body(topics)
    }
}