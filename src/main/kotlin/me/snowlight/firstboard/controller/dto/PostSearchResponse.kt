package me.snowlight.firstboard.controller.dto

import me.snowlight.firstboard.service.dto.PostSearchResponseDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import java.time.LocalDateTime

data class PostSearchResponse(
    val id: Long?,
    val title: String?,
    val tag: String? = null,
    val createdBy: String?,
    val createdAt: LocalDateTime?,
)

fun Page<PostSearchResponseDto>.toResponse() = PageImpl(
    content.map { it.toResponse() },
    pageable,
    totalElements
)

fun PostSearchResponseDto.toResponse() = PostSearchResponse(
    id = id,
    title = title,
    createdBy = createdBy,
    createdAt = createdAt
)
