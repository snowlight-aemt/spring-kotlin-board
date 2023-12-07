package me.snowlight.firstboard.service.dto

import me.snowlight.firstboard.domain.Tag
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl

fun Page<Tag>. toPageSearchResponseDto(countLike: (Long) -> Long) = PageImpl(
    content.map { it.toSearchResponseDto(countLike) },
    pageable,
    totalElements,
)

fun Tag.toSearchResponseDto(countLike: (Long) -> Long) = PostSearchResponseDto(
    id = this.post.id,
    title = this.post.title,
    likeCount = countLike(this.post.id),
    createdBy = this.post.createdBy,
    createdAt = this.post.createdAt,
    tag = this.name,
)
