package me.snowlight.firstboard.repository

import me.snowlight.firstboard.domain.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository: JpaRepository<Post, Long> {
    fun findByTitle(title: String): List<Post>
}
