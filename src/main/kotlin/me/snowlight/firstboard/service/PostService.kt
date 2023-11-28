package me.snowlight.firstboard.service

import me.snowlight.firstboard.exception.PostNotDeletableException
import me.snowlight.firstboard.exception.PostNotFoundException
import me.snowlight.firstboard.repository.PostRepository
import me.snowlight.firstboard.service.dto.PostCreateDto
import me.snowlight.firstboard.service.dto.PostDeleteDto
import me.snowlight.firstboard.service.dto.PostUpdateDto
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
}
