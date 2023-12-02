package me.snowlight.firstboard.controller

import me.snowlight.firstboard.controller.dto.CommentCreateRequest
import me.snowlight.firstboard.controller.dto.CommentUpdateRequest
import org.springdoc.core.annotations.ParameterObject
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
@RestController
class CommentController {
    @PostMapping("/posts/{postId}/comments")
    fun createComment(
        @PathVariable postId: Long,
        @RequestBody request: CommentCreateRequest,
    ): ResponseEntity<Long> {
        println("Post /posts/${postId}/comments")
        return ok(1L)
    }

    @PutMapping("/comments/{id}")
    fun updateComment(
        @PathVariable id: Long,
        @RequestBody request: CommentUpdateRequest,
    ): ResponseEntity<Long> {
        println("Put /comments/${id}")
        return ok(id)
    }

    @DeleteMapping("/comments/{id}")
    fun deleteComment(
        @PathVariable id: Long,
        @RequestParam deletedBy: String,
    ): ResponseEntity<Long> {
        println("Delete /comments/${id}?deletedBy=${deletedBy}")
        return ok(id)
    }
}

