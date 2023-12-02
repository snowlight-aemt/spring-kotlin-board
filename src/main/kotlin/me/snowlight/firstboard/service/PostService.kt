package me.snowlight.firstboard.service

import me.snowlight.firstboard.exception.PostNotDeletableException
import me.snowlight.firstboard.exception.PostNotFoundException
import me.snowlight.firstboard.repository.PostRepository
import me.snowlight.firstboard.service.dto.PostCreateDto
import me.snowlight.firstboard.service.dto.PostDeleteDto
import me.snowlight.firstboard.service.dto.PostDetailResponseDto
import me.snowlight.firstboard.service.dto.PostSearchRequestDto
import me.snowlight.firstboard.service.dto.PostSearchResponseDto
import me.snowlight.firstboard.service.dto.PostUpdateDto
import me.snowlight.firstboard.service.dto.toPageSearchResponseDto
import me.snowlight.firstboard.service.dto.toPostDetailResponseDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class PostService(
    val postRepository: PostRepository
) {
    @Transactional
    fun createPost(postCreateDto: PostCreateDto) =
        postRepository.save(postCreateDto.toEntity()).id

    @Transactional
    fun updatePost(id: Long, postUpdateDto: PostUpdateDto): Long {
        val post = postRepository.findByIdOrNull(id)?: throw PostNotFoundException()
        post.update(postUpdateDto)
        return id
    }

    @Transactional
    fun deletePost(id: Long, postDeleteDto: PostDeleteDto): Long {
        val post = postRepository.findByIdOrNull(id)?: throw PostNotFoundException()
        if (postDeleteDto.updatedBy != post.createdBy) throw PostNotDeletableException()

        postRepository.delete(post)
        return id;
    }

    fun getPost(id: Long): PostDetailResponseDto {
        return postRepository.findByIdOrNull(id)?.toPostDetailResponseDto() ?: throw PostNotFoundException()
    }

    // TODO Page 하는 방법
    fun getPageBy(page: Pageable, postSearchRequestDto: PostSearchRequestDto): Page<PostSearchResponseDto> {
        return postRepository.findPageBy(page, postSearchRequestDto).toPageSearchResponseDto()
    }
}
