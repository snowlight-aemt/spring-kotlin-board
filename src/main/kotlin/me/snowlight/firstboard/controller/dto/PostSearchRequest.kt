package me.snowlight.firstboard.controller.dto

import me.snowlight.firstboard.service.dto.PostSearchRequestDto
import org.springframework.web.bind.annotation.RequestParam

data class PostSearchRequest(
    @RequestParam
    val title: String? = null,
    @RequestParam
    val createdBy: String? = null,
)

fun PostSearchRequest.toDto(): PostSearchRequestDto {
    return PostSearchRequestDto(
        title = this.title,
        createdBy = this.createdBy
    )
}
