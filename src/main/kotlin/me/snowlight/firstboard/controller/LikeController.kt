package me.snowlight.firstboard.controller

import me.snowlight.firstboard.controller.dto.LikeCreateRequest
import me.snowlight.firstboard.service.LikeService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LikeController(
    val likeService: LikeService,
) {
    @PostMapping("/posts/{id}/likes")
    fun createLike(@PathVariable id: Long, @RequestBody request: LikeCreateRequest): ResponseEntity<Long> {
        return ResponseEntity.ok(likeService.createLike(id, request.toDto()))
    }
}
