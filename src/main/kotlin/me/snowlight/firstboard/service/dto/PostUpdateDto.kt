package me.snowlight.firstboard.service.dto

import me.snowlight.firstboard.domain.Post

data class PostUpdateDto (
    val title: String,
    val content: String,
    val updatedBy: String,
)
