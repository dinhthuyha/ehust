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
        return userService.signIn(id, password);
    }

    @GetMapping("/users")
    fun getAllArticles(): List<User> =
        userService.findAll()

    //TODO; get profile
    @GetMapping("/user/profile/{id}")
    fun findProfileById(@PathVariable(value = "id") id: Int)= userService.findProfileById(id)

    //TODO: lay danh sach lop sinh vien
    @GetMapping("/classstudent/{grade}")
    fun findListStudentInClass(@PathVariable(value = "grade") grade: String) = userService.findALlStudentInClass(grade)

    //TODO: Lay danh sach cac project da lam
    @GetMapping("/user/projects/{id}")
    fun findAllProjectsByStudentId(@PathVariable(value = "id") id: Int) = userService.findAllProjectsByIdStudent(id)

    //TODO tim kiem sinh vien theo fullname
    @GetMapping("/search/user/name/{fullName}")
    fun findUserByFullName(@PathVariable(value = "fullName") fullName: String) = userService.findUserByFullName(fullName)

    @GetMapping("/search/user/id/{id}")
    fun findUserById(@PathVariable(value = "id") id: Int) = userService.findUserById(id)




}