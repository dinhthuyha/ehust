package com.hadt.ehust.security

import com.hadt.ehust.entities.User
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.Duration
import java.time.Instant
import java.util.*
import java.util.concurrent.TimeUnit
import javax.annotation.PostConstruct
import javax.crypto.SecretKey

@Service
class JwtUtils {
    @Value("\${security.jwt.token.secret-key:secret-key}")
    private var base64Secret: String? = null

    @Value("\${security.jwt.token.expire-length:3600000}")
    private var validityInMilliseconds: Long = 3600000 //1h

    private lateinit var secretKey: SecretKey

    @PostConstruct
    protected fun init() {
        secretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(base64Secret))
    }

    fun createToken( user: User): Map<String, String?> {
        val hashMap = hashMapOf<String, String?>()
        hashMap["id"] = user.id.toString()
        hashMap["role_id"] = user.role?.name
        hashMap["full_name"] = user.fullName
        hashMap["grade"] = user.grade ?: ""
        hashMap["institute_of_management"] = user.instituteOfManagement
        hashMap["course"] = user.course ?: ""
        hashMap["email"] = user.email
        hashMap["cadre_status"] = user.cadreStatus ?: ""
        hashMap["unit"] = user.unit.toString()
        hashMap["image_background"] = user.imageBackground
        hashMap["image_avatar"] = user.imageAvatar
        return hashMap
    }

    fun generateAuthToken(user: UserDetailsImpl): Map<String, Any> {
        val response = hashMapOf<String, Any>()
        val token = Jwts.builder()
            .setSubject(user.username)
            .signWith(secretKey, SignatureAlgorithm.HS384)
            .setExpiration(Date(Instant.now().toEpochMilli() + TimeUnit.MILLISECONDS.convert(Duration.of(7, TimeUnit.DAYS.toChronoUnit()))))
            .compact()
        response["token"] = token
        response["profile"] = createToken(user.user)
        return response
    }

    fun validateAuthToken(jwt: String?): Jws<Claims> {
        return Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(jwt)
    }
}