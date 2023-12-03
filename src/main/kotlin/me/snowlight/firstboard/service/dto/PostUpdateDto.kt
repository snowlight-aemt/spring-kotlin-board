package me.snowlight.firstboard.service.dto

data class PostUpdateDto(
    val title: String,
    val content: String,
    val updatedBy: String,
    val tags: MutableList<String> = mutableListOf(),
)
