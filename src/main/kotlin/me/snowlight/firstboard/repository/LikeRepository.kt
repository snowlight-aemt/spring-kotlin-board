package me.snowlight.firstboard.repository

import me.snowlight.firstboard.domain.Like
import org.springframework.data.jpa.repository.JpaRepository

interface LikeRepository: JpaRepository<Like, Long>
