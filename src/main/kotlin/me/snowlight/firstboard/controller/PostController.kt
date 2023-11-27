package me.snowlight.firstboard.controller

import me.snowlight.firstboard.controller.dto.PostCreateRequest
import me.snowlight.firstboard.controller.dto.PostDetailResponse
import me.snowlight.firstboard.controller.dto.PostSearchRequest
import me.snowlight.firstboard.controller.dto.PostSearchResponse
import me.snowlight.firstboard.controller.dto.PostUpdateRequest
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
class PostController {
    @PostMapping
    fun create(@RequestBody request: PostCreateRequest) =
        ResponseEntity.ok(1L)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody request: PostUpdateRequest) =
        ResponseEntity.ok(id)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long, @RequestParam createdBy: String) =
        ResponseEntity.ok(id)

    @GetMapping("/{id}")
    fun getPost(@PathVariable id: Long) =
        ResponseEntity.ok(
            PostDetailResponse(
                id = id,
                title = "title",
                content = "content",
                createdBy = "createdBy"
            )
        )

    @GetMapping
    fun getPosts(
        pageable: Pageable,
        request: PostSearchRequest,
    ): ResponseEntity<Page<PostSearchResponse>> =
        ResponseEntity.ok(Page.empty(pageable))
}
