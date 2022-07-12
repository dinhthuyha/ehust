package com.hadt.ehust.repository

import com.hadt.ehust.entities.MoreInformationTopic
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MoreInformationTopicRepository: JpaRepository<MoreInformationTopic, Int> {

}