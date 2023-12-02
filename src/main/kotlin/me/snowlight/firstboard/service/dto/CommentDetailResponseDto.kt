package me.snowlight.firstboard.service.dto

import java.time.LocalDateTime

data class CommentDetailResponseDto (
    val id: Long,
    val content: String,
    val createdBy: String,
    val createdAt: LocalDateTime,
)
