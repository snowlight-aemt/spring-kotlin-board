package me.snowlight.firstboard.controller.dto

import jakarta.validation.constraints.NotEmpty

data class CommentCreateRequest (
    @NotEmpty
    val title: String,
    @NotEmpty
    val content: String,
    @NotEmpty
    val createdBy: String
)
