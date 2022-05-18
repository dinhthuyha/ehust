package com.hadt.ehust.security

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


    fun createToken(id: Int , user: ResponseEntity<UserResponse>):String {
        val claims = Jwts.claims()
        claims["id"] = user.body?.id
        claims["role_id"] = user.body?.roleId
        claims["full_name"] = user.body?.fullName
        claims["grade"] = user.body?.grade
        claims["institute_of_management"] = user.body?.instituteOfManagement
        claims["gender"] = user.body?.gender
        claims["course"] = user.body?.course
        claims["email"] = user.body?.email
        claims["cadre_status"] = user.body?.cadreStatus
        claims["unit"] = user.body?.unit
        claims["image_background"] = user.body?.imageBackground
        claims["image_avatar"] = user.body?.imageAvatar

        val now = Date()
        val validity = Date(now.time + validityInMilliseconds)
        return Jwts.builder()
            .setHeaderParam("typ","JWT")
            .setClaims(claims)
            .setExpiration(validity)
            .setIssuedAt(now)
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact()
    }

    fun generateAuthToken(user: UserDetailsImpl): String {
        return Jwts.builder()
            .setSubject(user.username)
            .signWith(keyPair.private)
            .compact()
    }

    fun validateAuthToken(jwt: String?): Jws<Claims> {
        return Jwts.parserBuilder()
            .setSigningKey(keyPair.public)
            .build()
            .parseClaimsJws(jwt)
    }
}