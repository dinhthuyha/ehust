package com.hadt.ehust.security

import com.hadt.ehust.entities.User
import com.hadt.ehust.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service("myImpl")
class UserDetailsServiceImpl(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        return username?.let {
            userRepository.findByIdOrNull(Integer.parseInt(it))
        }?.let {
            UserDetailsImpl(it)
        } ?: throw UsernameNotFoundException("username not found or null")
    }
}

class UserDetailsImpl(
    private val user: User
): UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        //TODO: get roles from user entity
        return mutableListOf(SimpleGrantedAuthority("ROLE_ADMIN"))
    }

    override fun getPassword(): String {
        return user.password
    }

    override fun getUsername(): String {
        return user.id.toString()
    }

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}