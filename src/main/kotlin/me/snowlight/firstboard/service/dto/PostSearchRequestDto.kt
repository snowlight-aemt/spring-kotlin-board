package me.snowlight.firstboard.service.dto

data class PostSearchRequestDto(
    val title: String? = null,
    val tag: String? = null,
    val createdBy: String? = null,
)
