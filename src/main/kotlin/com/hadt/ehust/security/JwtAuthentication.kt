package com.hadt.ehust.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthenticationTokenProvider(
    private val jwtUtils: JwtUtils,
    @Qualifier("myImpl") private val userDetailsService: UserDetailsService
): AuthenticationProvider {
    override fun authenticate(authentication: Authentication?): Authentication {
        authentication as JwtAuthenticationToken
        val jwt = jwtUtils.validateAuthToken(authentication.jwtString)
        val user = userDetailsService.loadUserByUsername(jwt.body.subject)
        return JwtAuthenticationToken(jwt, user.authorities)
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return authentication?.let { JwtAuthenticationToken::class.java.isAssignableFrom(it) } ?: false
    }
}

class JwtAuthenticationToken : AbstractAuthenticationToken {
    var jwtString: String? = null
    private var jwt: Jws<Claims>? = null

    constructor(jwtString: String): super(null) {
        this.jwtString = jwtString
        isAuthenticated = false
    }

    constructor(jwt: Jws<Claims>, authorities: MutableCollection<out GrantedAuthority>?) : super(authorities) {
        this.jwt = jwt
        isAuthenticated = true
    }

    override fun getCredentials(): Any {
        return jwt as Any
    }

    override fun getPrincipal(): Any {
        return jwt?.body?.subject as Any
    }

}

class JwtAuthenticationTokenFilter(
    private val authenticationManager: AuthenticationManager
): OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val jwtString = parseJwt(request)
        jwtString?.let {
            val authenticationToken = JwtAuthenticationToken(it)
            val authenticatedToken = authenticationManager.authenticate(authenticationToken)
            SecurityContextHolder.getContext().authentication = authenticatedToken
        }
        filterChain.doFilter(request, response)
    }

    private fun parseJwt(request: HttpServletRequest): String? {
        val headerAuth = request.getHeader(HttpHeaders.AUTHORIZATION) ?: ""
        return if (headerAuth.isNotBlank() && headerAuth.startsWith("Bearer ")) {
            headerAuth.substring(7, headerAuth.length)
        } else null
    }

}