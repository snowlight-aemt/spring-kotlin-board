package me.snowlight.firstboard.controller

import me.snowlight.firstboard.controller.dto.PostCreateRequest
import me.snowlight.firstboard.controller.dto.PostDetailResponse
import me.snowlight.firstboard.controller.dto.PostSearchRequest
import me.snowlight.firstboard.controller.dto.PostSearchResponse
import me.snowlight.firstboard.controller.dto.PostUpdateRequest
import me.snowlight.firstboard.controller.dto.toDto
import me.snowlight.firstboard.controller.dto.toPostResponse
import me.snowlight.firstboard.controller.dto.toResponse
import me.snowlight.firstboard.service.PostService
import me.snowlight.firstboard.service.dto.PostDeleteDto
import org.springdoc.core.annotations.ParameterObject
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/posts")
class PostController(
    val postService: PostService,
) {
    @PostMapping
    fun create(@RequestBody request: PostCreateRequest) =
        ResponseEntity.ok(postService.createPost(request.toDto()))

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody request: PostUpdateRequest) =
        ResponseEntity.ok(postService.updatePost(id, request.toDto()))

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long, @RequestParam createdBy: String) =
        ResponseEntity.ok(postService.deletePost(id, PostDeleteDto(createdBy)))

    @GetMapping("/{id}")
    fun getPost(@PathVariable id: Long) : ResponseEntity<PostDetailResponse> =
        ResponseEntity.ok(postService.getPost(id).toPostResponse())

    @GetMapping
    fun getPosts(
        pageable: Pageable,
        @ParameterObject request: PostSearchRequest,
    ): ResponseEntity<Page<PostSearchResponse>> =
        ResponseEntity.ok(postService.getPageBy(pageable, request.toDto()).toResponse())
}
