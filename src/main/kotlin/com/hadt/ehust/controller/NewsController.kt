package com.hadt.ehust.controller

import com.hadt.ehust.service.NewsService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class NewsController(
    private val newsService: NewsService
) {
    @GetMapping("/news")
    fun getAllNews()= newsService.getAllNews()
}