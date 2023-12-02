package me.snowlight.firstboard.controller.dto

import jakarta.validation.constraints.NotEmpty

data class CommentCreateRequest (
    @NotEmpty
    val content: String,
    @NotEmpty
    val createdBy: String
)
