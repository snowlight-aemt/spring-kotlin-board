package me.snowlight.firstboard.service.dto

import me.snowlight.firstboard.domain.Comment
import me.snowlight.firstboard.domain.Post

data class CommentCreateDto (
    val content: String,
    val createdBy: String,
)

fun CommentCreateDto.toEntity(post: Post): Comment {
    return Comment(
        content = content,
        createdBy = createdBy,
        post = post,
    )
}
