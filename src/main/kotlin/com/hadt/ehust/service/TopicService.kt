package com.hadt.ehust.service

import com.hadt.ehust.model.StatusTopic
import com.hadt.ehust.repository.TopicRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class TopicService(private val topicRepository: TopicRepository) {

    fun updateTopicStatus(name: String, status: StatusTopic): ResponseEntity<Unit> {
        topicRepository.findByName(name)?.copy(status = status)?.let {
            topicRepository.save(it)
        }
        return ResponseEntity<Unit>(HttpStatus.OK)
    }
}