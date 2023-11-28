package me.snowlight.firstboard.controller.dto

import me.snowlight.firstboard.service.dto.PostCreateDto

data class PostCreateRequest(
    val title: String,
    val content: String,
    val createdBy: String,
)

fun PostCreateRequest.toDto() = PostCreateDto(this.title, this.content, this.createdBy)
