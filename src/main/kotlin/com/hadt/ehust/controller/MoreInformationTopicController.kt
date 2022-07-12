package com.hadt.ehust.controller

import com.hadt.ehust.service.MoreInformationTopicService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class MoreInformationTopicController(private val moreInformationTopicService: MoreInformationTopicService) {
    @GetMapping("detail/topic/{id}")
    fun findByDetailTopic(@PathVariable("id") id: Int) = moreInformationTopicService.findById(id)
}