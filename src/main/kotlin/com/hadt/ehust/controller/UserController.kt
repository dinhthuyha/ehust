package com.hadt.ehust.controller

import com.hadt.ehust.service.UserService
import com.hadt.ehust.entities.User
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class UserController(
    private val userService: UserService
) {
    @PostMapping("/signin")
    fun login(@RequestParam(name = "id") id: Int, @RequestParam(name = "password") password: String): String {
        return userService.signIn(20173086, "123456");
    }
    @GetMapping("/users")
    fun getAllArticles(): List<User> =
        userService.findAll()


}