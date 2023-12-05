package me.snowlight.firstboard.controller.dto

import java.time.LocalDateTime

data class TagDetailResponse(
    val id: Long,
    val name: String,
    val createdBy: String,
    val createdAt: LocalDateTime,
)
