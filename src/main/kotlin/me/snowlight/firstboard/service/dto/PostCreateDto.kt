package me.snowlight.firstboard.service.dto

import me.snowlight.firstboard.domain.Post

data class PostCreateDto(
    val title: String,
    val content: String,
    val createdBy: String
) {
    fun toEntity() = Post(
        title= title,
        content = content,
        createdBy = createdBy,
    )
}
