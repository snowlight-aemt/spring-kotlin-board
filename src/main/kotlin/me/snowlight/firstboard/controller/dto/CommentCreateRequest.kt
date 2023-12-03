package me.snowlight.firstboard.controller.dto

import jakarta.validation.constraints.NotEmpty
import me.snowlight.firstboard.service.dto.CommentCreateDto

data class CommentCreateRequest (
    @NotEmpty
    val content: String,
    @NotEmpty
    val createdBy: String,
) {
    fun toDto() = CommentCreateDto(this.content, this.createdBy)
}
