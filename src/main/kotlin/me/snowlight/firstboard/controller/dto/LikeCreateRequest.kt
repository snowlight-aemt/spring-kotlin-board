package me.snowlight.firstboard.controller.dto

import me.snowlight.firstboard.service.dto.LikeCreateDto

data class LikeCreateRequest(
    val createdBy: String,
) {
    fun toDto() = LikeCreateDto(createdBy = this.createdBy)
}
