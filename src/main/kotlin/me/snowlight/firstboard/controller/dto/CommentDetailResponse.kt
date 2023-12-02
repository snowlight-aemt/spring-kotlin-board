package me.snowlight.firstboard.controller.dto

import java.time.LocalDateTime

data class CommentDetailResponse (
    val id: Long,
    val content: String,
    val createdBy: String,
    val createdAt: LocalDateTime,
)
