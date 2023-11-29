package me.snowlight.firstboard.service.dto

import me.snowlight.firstboard.controller.dto.PostDetailResponse
import me.snowlight.firstboard.domain.Post
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime

data class PostDetailResponseDto (
    val id: Long,
    val title: String,
    val content: String,
    val createdBy: String,
    val createdDate: LocalDateTime,
)

fun Post.toPostDetailResponseDto(): PostDetailResponseDto {
    return PostDetailResponseDto(
        id = this.id,
        title = this.title,
        content = this.content,
        createdBy = this.createdBy,
        createdDate = this.createdDate,
    )
}
