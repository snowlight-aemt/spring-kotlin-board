package me.snowlight.firstboard.controller.dto

import me.snowlight.firstboard.service.dto.PostUpdateDto

data class PostUpdateRequest(
    val title: String,
    val content: String,
    val updatedBy: String,
)

fun PostUpdateRequest.toDto() = PostUpdateDto(
    title = this.title,
    content = this.content,
    updatedBy = this.updatedBy,
)
