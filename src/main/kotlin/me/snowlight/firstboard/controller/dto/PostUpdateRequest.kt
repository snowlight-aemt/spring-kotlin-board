package me.snowlight.firstboard.controller.dto

data class PostUpdateRequest(
    val title: String,
    val content: String,
    val createdBy: String,
)
