package me.snowlight.firstboard.controller.dto

import me.snowlight.firstboard.service.dto.PostCreateDto

data class PostCreateRequest(
    val title: String,
    val content: String,
    val createdBy: String,
    // TODO empty list 와 초기화 값 없이(null) 어느 쪽에 더 좋을까요?
    val tags: MutableList<String> = mutableListOf(),
)

fun PostCreateRequest.toDto() = PostCreateDto(this.title, this.content, this.createdBy, this.tags)
