package com.hadt.ehust.controller

import com.hadt.ehust.entities.News
import com.hadt.ehust.entities.Task
import com.hadt.ehust.model.StatusNotification
import com.hadt.ehust.model.TypeNotification
import com.hadt.ehust.service.NewsService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
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

    @PostMapping("/notification/new/task")
    fun postNotificationNewTask(@RequestBody news: News) = newsService.postNotificationNewTask(news)

    @PutMapping("/notification/update/task")
    fun notificationUpdateTask(@RequestBody task: Task) = newsService.notificationUpdateTask(task)

    @PostMapping("/clear/notification/read")
    fun clearNotificationRead(@RequestBody newsReads: List<News>) = newsService.clearNotificationRead(newsReads)
}