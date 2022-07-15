package com.hadt.ehust.repository

import com.hadt.ehust.entities.News
import com.hadt.ehust.model.TypeNotification
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface NewsRepository: JpaRepository<News, Int> {
    fun findByType(type: TypeNotification): List<News>
}