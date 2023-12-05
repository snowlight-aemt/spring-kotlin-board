package me.snowlight.firstboard.controller

import me.snowlight.firstboard.controller.dto.LikeCreateRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LikeController {
    @PostMapping("/posts/{id}/likes")
    fun createLike(@PathVariable id: String, @RequestBody request: LikeCreateRequest): ResponseEntity<Long> {
        return ResponseEntity.ok(1L)
    }
}
