package com.hadt.ehust.utils

import com.hadt.ehust.model.Role
import org.springframework.security.core.context.SecurityContextHolder

object Utils {

    fun hasRole(role: Role): Boolean {
        return SecurityContextHolder.getContext().authentication.authorities
            .map { it.authority }
            .any { it == role.name }
    }
}