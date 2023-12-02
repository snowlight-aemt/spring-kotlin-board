package me.snowlight.firstboard.controller.dto

import me.snowlight.firstboard.service.dto.PostDetailResponseDto
import java.time.LocalDateTime

data class PostDetailResponse(
    val id: Long,
    val title: String,
    val content: String,
    val createdBy: String,
    val createdAt: LocalDateTime,
)

fun PostDetailResponseDto.toPostResponse() : PostDetailResponse {
    return PostDetailResponse(
        id = id,
        title = title,
        content = content,
        createdBy = createdBy,
        createdAt = createdAt,
    )
}
