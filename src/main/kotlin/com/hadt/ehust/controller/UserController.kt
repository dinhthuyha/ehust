package com.hadt.ehust.controller

import com.hadt.ehust.service.UserService
import com.hadt.ehust.entities.User
import com.hadt.ehust.model.Role
import com.hadt.ehust.utils.Utils
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class UserController(
    private val userService: UserService
) {
    @GetMapping("/checkpoint")
    fun checkToken(): ResponseEntity<Any> = userService.checkToken()

    @PostMapping("/signin")
    fun login(@RequestParam(name = "id") id: Int, @RequestParam(name = "password") password: String): Map<String,Any> {
        return userService.signIn(id, password);
    }

    @GetMapping("/users")
    fun getAllArticles(): List<User> =
        userService.findAll()

    /**
     * get profile
     */

    @GetMapping("/user/profile/{id}")
    fun findProfileById(@PathVariable(value = "id") id: Int)= userService.findProfileById(id)

    /**
     * TODO: lay danh sach lop sinh vien
     */
    @GetMapping("/classstudent/{grade}")
    fun findListStudentInClass(@PathVariable(value = "grade") grade: String) = userService.findALlStudentInClass(grade)

    /**
     * TODO: Lay danh sach cac project da lam
     */
    @GetMapping("/user/projects/{id}")
    fun findAllProjectsById(@PathVariable(value = "id") id: Int) = userService.findAllProjectsById(id)

    /**
     * TODO tim kiem sinh vien theo fullname
     */
    @GetMapping("/search/user/name/{fullName}/{roleId}")
    fun findUserByFullName(@PathVariable(value = "fullName") fullName: String, @PathVariable(value = "roleId") roleId: Role) = userService.findUserByFullName(fullName, roleId)

    @GetMapping("/search/user/id/{id}/{roleId}")
    fun findUserById(@PathVariable(value = "id") id: Int, @PathVariable(value = "roleId") roleId: Role) = userService.findUserByIdAndRole(id, roleId)

    @GetMapping("/user/{id}/schedule")
    fun findByScheduleByIdStudent(@PathVariable(value = "id") id: Int) = userService.findByScheduleByIdStudent(id)

    @GetMapping("/find/all/user/{full_name}")
    fun searchAllUserByFullName(@PathVariable("full_name") fullName: String) = userService.searchAllUserByFullName(fullName)

}