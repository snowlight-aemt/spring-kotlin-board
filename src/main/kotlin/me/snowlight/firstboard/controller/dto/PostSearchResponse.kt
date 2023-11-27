package me.snowlight.firstboard.controller.dto

import java.time.LocalDateTime

data class PostSearchResponse(
    val id: Long?,
    val title: String?,
    val createdBy: String?,
    val createdAt: LocalDateTime?,
)
