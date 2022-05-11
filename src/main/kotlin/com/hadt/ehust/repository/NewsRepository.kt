package com.hadt.ehust.repository

import com.hadt.ehust.entities.News
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface NewsRepository: JpaRepository<News, Int> {
}