package com.hadt.ehust.controller

import com.hadt.ehust.model.StatusNotification
import com.hadt.ehust.model.TypeNotification
import com.hadt.ehust.service.NewsService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class NewsController(
    private val newsService: NewsService
) {
    @GetMapping("/news/{type}")
    fun getAllNewsByType(@PathVariable("type") type: TypeNotification)= newsService.findAllNews(type)

    @PutMapping("/new/update/status/{id}/{type}")
    fun updateStatusNew( @RequestParam("status") status: StatusNotification,@PathVariable("id") id: Int ,@PathVariable("type") type: TypeNotification) =
        newsService.updateStatusNews(id, status, type)
}