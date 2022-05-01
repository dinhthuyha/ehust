package com.hadt.ehust.service

import com.hadt.ehust.entities.User
import com.hadt.ehust.exception.CustomException
import com.hadt.ehust.repository.UserRepository
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
            return jwtTokenProvider.createToken(id, getUserById(id))
        } catch (e: Exception) {
            throw CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    fun getUserById(id: Int): ResponseEntity<User> {
        return userRepository.findById(id).map { article ->
            ResponseEntity.ok(article)
        }.orElse(ResponseEntity.notFound().build())
    }

    fun findAll(): List<User> {
        return userRepository.findAll()
    }

}