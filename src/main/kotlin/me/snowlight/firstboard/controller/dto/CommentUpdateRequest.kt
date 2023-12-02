package me.snowlight.firstboard.controller.dto

import jakarta.validation.constraints.NotEmpty

data class CommentUpdateRequest (
    @NotEmpty
    val content: String,
    @NotEmpty
    val updatedBy: String,
)
