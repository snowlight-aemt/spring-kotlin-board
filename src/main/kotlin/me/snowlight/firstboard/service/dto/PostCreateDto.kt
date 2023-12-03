package me.snowlight.firstboard.service.dto

import me.snowlight.firstboard.domain.Post

data class PostCreateDto(
    val title: String,
    val content: String,
    val createdBy: String,
    // TODO request dto 에서 이미 초기화 했는데, 또 초기화가 필요할까?
    val tags: MutableList<String> = mutableListOf(),
) {
    fun toEntity() = Post(
        title = title,
        content = content,
        createdBy = createdBy,
        tags = tags,
    )
}
