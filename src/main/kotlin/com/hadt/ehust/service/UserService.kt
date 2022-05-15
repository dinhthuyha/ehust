package com.hadt.ehust.service

import com.hadt.ehust.entities.ClassStudent
import com.hadt.ehust.entities.User
import com.hadt.ehust.exception.CustomException
import com.hadt.ehust.repository.UserRepository
import com.hadt.ehust.response.ProfileResponse
import com.hadt.ehust.security.JwtTokenProvider
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,

    private val jwtTokenProvider: JwtTokenProvider
) {
    fun signIn(id: Int, password: String): String {
        try {
            // authenticationManager.authenticate(UsernamePasswordAuthenticationToken(id, password))
            return jwtTokenProvider.createToken(id, findUserById(id))
        } catch (e: Exception) {
            throw CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    fun findUserById(id: Int): ResponseEntity<User> {
        return userRepository.findById(id).map { article ->
            ResponseEntity.ok(article)
        }.orElse(ResponseEntity.notFound().build())
    }
    fun findUserByFullName(fullName: String): ResponseEntity<User>{
        return userRepository.findUserByFullName(fullName).map { user ->
            ResponseEntity.ok(user)
        }.orElse(ResponseEntity.notFound().build())
    }

    fun findProfileById(id: Int): ResponseEntity<ProfileResponse> {
        return userRepository.findById(id).map { article ->
            ResponseEntity.ok(ProfileResponse(article.id, article.fullName))
        }.orElse(ResponseEntity.notFound().build())
    }

    fun findALlStudentInClass(grade: String): ResponseEntity<List<User?>>? {
        return userRepository.getListStudentInClass(grade).map { users ->
            ResponseEntity.ok(users)
        }.orElse(ResponseEntity.notFound().build())
    }
    fun findAllProjectsByIdStudent(id: Int): ResponseEntity<List<ClassStudent>>{
        return userRepository.findById(id).map { user ->
            val projects = user.likedClasses.toList()
            ResponseEntity.ok(projects)
        }.orElse(ResponseEntity.notFound().build())
    }

    fun findAll(): List<User> {
        return userRepository.findAll()
    }



}