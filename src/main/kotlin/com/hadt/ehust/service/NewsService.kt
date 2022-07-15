package com.hadt.ehust.service

import com.hadt.ehust.entities.News
import com.hadt.ehust.model.StatusNotification
import com.hadt.ehust.model.TypeNotification
import com.hadt.ehust.repository.NewsRepository
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class NewsService(
    private val newsRepository: NewsRepository
) {
    fun findAllNews(type: TypeNotification)= newsRepository.findByType(type)

    fun updateStatusNews(id: Int, status: StatusNotification, type: TypeNotification): List<News>{
        newsRepository.findById(id).map {
            val news = it.copy(status = status)
            newsRepository.save(news)
        }
        return newsRepository.findByType(type)
    }


}