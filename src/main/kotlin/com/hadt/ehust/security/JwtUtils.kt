package com.hadt.ehust.security

import com.hadt.ehust.entities.User
import com.hadt.ehust.response.UserResponse
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.security.Key
import java.util.*
import javax.annotation.PostConstruct

@Service
class JwtUtils {
    @Value("\${security.jwt.token.secret-key:secret-key}")
    private var secretKey: String? = null

    @Value("\${security.jwt.token.expire-length:3600000}")
    private var validityInMilliseconds: Long = 3600000 //1h

    private val keyPair = Keys.keyPairFor(SignatureAlgorithm.ES256)

    @PostConstruct
    protected fun init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey!!.toByteArray())
    }


    fun createToken( user: User): Map<String, String> {
        val hashMap = hashMapOf<String, String>()
        hashMap["id"] = user.id.toString()
        hashMap["role_id"] = user.role.name
        hashMap["full_name"] = user.fullName
        hashMap["grade"] = user.grade
        hashMap["institute_of_management"] = user.instituteOfManagement
        hashMap["gender"] = user.gender
        hashMap["course"] = user.course
        hashMap["email"] = user.email
        hashMap["cadre_status"] = user.cadreStatus
        hashMap["unit"] = user.unit
        hashMap["image_background"] = user.imageBackground
        hashMap["image_avatar"] = user.imageAvatar
        return hashMap
    }

    fun generateAuthToken(user: UserDetailsImpl): Map<String, Any> {
        val response = hashMapOf<String, Any>()
        var token = Jwts.builder()
            .setSubject(user.username)
            .signWith(keyPair.private)
            .compact()
        response["token"] = token
        response["profile"] = createToken(user.user)
        return response
    }

    fun validateAuthToken(jwt: String?): Jws<Claims> {
        return Jwts.parserBuilder()
            .setSigningKey(keyPair.public)
            .build()
            .parseClaimsJws(jwt)
    }
}