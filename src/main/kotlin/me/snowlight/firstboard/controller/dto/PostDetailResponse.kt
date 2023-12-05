package me.snowlight.firstboard.controller.dto

import me.snowlight.firstboard.service.dto.PostDetailResponseDto
import java.time.LocalDateTime

data class PostDetailResponse(
    val id: Long,
    val title: String,
    val content: String,
    val createdBy: String,
    val createdAt: LocalDateTime,
    val likeCount: Long? = 0L,
    val comments: List<CommentDetailResponse> = emptyList(),
    val tags: MutableList<TagDetailResponse> = mutableListOf(),
)

fun PostDetailResponseDto.toPostResponse(): PostDetailResponse {
    return PostDetailResponse(
        id = id,
        title = title,
        content = content,
        createdBy = createdBy,
        createdAt = createdAt,
        comments = comments.map {
            CommentDetailResponse(
                id = it.id,
                content = it.content,
                createdBy = it.createdBy,
                createdAt = it.createdAt
            )
        }
    )
}
