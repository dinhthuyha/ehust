package com.hadt.ehust.service

import com.hadt.ehust.entities.News
import com.hadt.ehust.repository.NewsRepository
import org.springframework.stereotype.Service

@Service
class NewsService(
    private val newsRepository: NewsRepository
) {
    fun findAllNews()= newsRepository.findAll()


}