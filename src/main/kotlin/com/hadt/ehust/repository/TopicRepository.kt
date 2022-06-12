package com.hadt.ehust.repository

import com.hadt.ehust.entities.Topic
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TopicRepository : JpaRepository<Topic,Int> {
}