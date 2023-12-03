package me.snowlight.firstboard.controller.dto

import jakarta.validation.constraints.NotEmpty
import me.snowlight.firstboard.service.dto.CommentUpdateDto

data class CommentUpdateRequest (
    @NotEmpty
    val content: String,
    @NotEmpty
    val updatedBy: String,
) {
    fun toDto() = CommentUpdateDto(this.content, this.updatedBy)
}
