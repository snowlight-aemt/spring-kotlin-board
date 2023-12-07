package me.snowlight.firstboard.service.dto

import me.snowlight.firstboard.domain.Post
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import java.time.LocalDateTime

data class PostSearchResponseDto(
    val id: Long,
    val title: String,
    val likeCount: Long,
    val createdBy: String,
    val createdAt: LocalDateTime,
    val tag: String? = null,
)

// TODO 함수 확장으로 다른 파일 안에서 상관없는 클래스에 함수를 확장할 수 있다.
fun Page<Post>.toPageSearchResponseDto(countLike: (Long) -> Long) = PageImpl(
    content.map { it.toPageSearchResponseDto(countLike) },
    pageable,
    totalElements
)

fun Post.toPageSearchResponseDto(countLike: (Long) -> Long) = PostSearchResponseDto(
    id = this.id,
    title = this.title,
    likeCount = countLike(this.id),
    createdBy = this.createdBy,
    createdAt = this.createdAt,
    tag = this.tags.firstOrNull()?.name,
)
