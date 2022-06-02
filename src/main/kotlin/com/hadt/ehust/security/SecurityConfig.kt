package com.hadt.ehust.security

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.http.HttpServletResponse

@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true)
class SecurityConfig(
    private val jwtAuthenticationTokenProvider: JwtAuthenticationTokenProvider,
    @Qualifier("myImpl") private val userDetailsService: UserDetailsService
): WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {
        http?.cors()?.disable()?.csrf()?.disable()
            ?.sessionManagement()
            ?.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            ?.and()
            ?.authorizeRequests()
            ?.antMatchers("/api/signin")?.permitAll()
            ?.anyRequest()?.authenticated()
            ?.and()
            ?.addFilterBefore(JwtAuthenticationTokenFilter(authenticationManager()), UsernamePasswordAuthenticationFilter::class.java)
            ?.exceptionHandling()
            ?.authenticationEntryPoint { _, response, authException ->
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.message)
            }
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(userDetailsService)
        auth?.authenticationProvider(jwtAuthenticationTokenProvider)
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder(10)
    }
}