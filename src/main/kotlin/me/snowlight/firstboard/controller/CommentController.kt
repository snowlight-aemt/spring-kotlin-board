package me.snowlight.firstboard.controller

import me.snowlight.firstboard.controller.dto.CommentCreateRequest
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
@RestController
class CommentController {
    @PostMapping("/posts/{id}/comments")
    fun createComment(
        @PathVariable id: Long,
        @RequestBody request: CommentCreateRequest,
    ): ResponseEntity<Long> {
        println("Post /posts/${id}/comments")
        return ok(id)
    }

    @PutMapping("/comments/{id}")
    fun updateComment(@PathVariable id: Long): ResponseEntity<Long> {
        println("Put /comments/${id}")
        return ok(id)
    }

    @DeleteMapping("/comments/{id}")
    fun deleteComment(@PathVariable id: Long): ResponseEntity<Long> {
        println("Delete /comments/${id}")
        return ok(id)
    }
}

