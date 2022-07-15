package com.hadt.ehust.service

import com.hadt.ehust.entities.News
import com.hadt.ehust.model.Role
import com.hadt.ehust.model.StatusNotification
import com.hadt.ehust.model.TypeNotification
import com.hadt.ehust.repository.NewsRepository
import com.hadt.ehust.repository.UserRepository
import com.hadt.ehust.utils.Utils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class NewsService(
    private val newsRepository: NewsRepository,
    private val userRepository: UserRepository
) {
    fun findAllNews(type: TypeNotification): List<News> {
       val news =
       return when(type == TypeNotification.TYPE_PROJECT){
            true -> {
                newsRepository.findByType(type).filter { it.idUserPost == Utils.getCurrentUserId() }
            }
            false -> {newsRepository.findByType(type)}
        }

    }

    fun updateStatusNews(id: Int, status: StatusNotification, type: TypeNotification): ResponseEntity<List<News>> {
        newsRepository.findById(id).map {
            val news = it.copy(status = status)
            newsRepository.save(news)
        }
        return ResponseEntity.ok().body(newsRepository.findByType(type))
    }

    fun postNotificationNewTask(news: News): ResponseEntity<HttpStatus> {
        newsRepository.save(news)
        return ResponseEntity.ok().body(HttpStatus.OK)
    }


}