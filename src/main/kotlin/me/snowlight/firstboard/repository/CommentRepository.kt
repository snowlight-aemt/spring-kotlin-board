package me.snowlight.firstboard.repository

import me.snowlight.firstboard.domain.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long>
