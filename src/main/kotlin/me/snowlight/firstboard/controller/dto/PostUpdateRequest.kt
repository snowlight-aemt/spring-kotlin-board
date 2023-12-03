package me.snowlight.firstboard.controller.dto

import me.snowlight.firstboard.service.dto.PostUpdateDto

data class PostUpdateRequest(
    val title: String,
    val content: String,
    val updatedBy: String,
    val tags: MutableList<String> = mutableListOf(),
)

fun PostUpdateRequest.toDto() = PostUpdateDto(
    title = this.title,
    content = this.content,
    updatedBy = this.updatedBy,
    tags = this.tags
)
