package me.snowlight.firstboard.controller

import me.snowlight.firstboard.controller.dto.CommentCreateRequest
import me.snowlight.firstboard.controller.dto.CommentUpdateRequest
import me.snowlight.firstboard.service.CommentService
import me.snowlight.firstboard.service.dto.CommentDeleteDto
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class CommentController(
    private val commentService: CommentService,
) {
    @PostMapping("/posts/{postId}/comments")
    fun createComment(
        @PathVariable postId: Long,
        @RequestBody request: CommentCreateRequest,
    ): ResponseEntity<Long> {
        return ok(commentService.createComment(postId, request.toDto()))
    }

    @PutMapping("/comments/{id}")
    fun updateComment(
        @PathVariable id: Long,
        @RequestBody request: CommentUpdateRequest,
    ): ResponseEntity<Long> {
        return ok(commentService.updateComment(id, request.toDto()))
    }

    @DeleteMapping("/comments/{id}")
    fun deleteComment(
        @PathVariable id: Long,
        @RequestParam deletedBy: String,
    ): ResponseEntity<Long> {
        return ok(commentService.deleteComment(id, CommentDeleteDto(deletedBy = deletedBy)))
    }
}
