package me.snowlight.firstboard.service.dto

import me.snowlight.firstboard.domain.Post
import java.time.LocalDateTime

data class PostDetailResponseDto(
    val id: Long,
    val title: String,
    val content: String,
    val likeCount: Long? = 0L,
    val createdBy: String,
    val createdAt: LocalDateTime,
    val comments: List<CommentDetailResponseDto>,
)

fun Post.toPostDetailResponseDto(countLike : (Long) -> Long): PostDetailResponseDto {
    return PostDetailResponseDto(
        id = this.id,
        title = this.title,
        content = this.content,
        likeCount = countLike(this.id),
        createdBy = this.createdBy,
        createdAt = this.createdAt,
        comments = this.comments.map {
            CommentDetailResponseDto(
                id = it.id,
                content = it.content,
                createdBy = it.createdBy,
                createdAt = it.createdAt
            )
        }
    )
}
