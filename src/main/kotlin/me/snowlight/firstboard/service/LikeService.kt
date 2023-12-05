package me.snowlight.firstboard.service

import me.snowlight.firstboard.domain.Like
import me.snowlight.firstboard.exception.PostNotFoundException
import me.snowlight.firstboard.repository.LikeRepository
import me.snowlight.firstboard.repository.PostRepository
import me.snowlight.firstboard.service.dto.LikeCreateDto
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class LikeService(
    val likeRepository: LikeRepository,
    val postRepository: PostRepository,
) {
    fun createLike(postId: Long, likeCreateDto: LikeCreateDto): Long {
        val post = postRepository.findByIdOrNull(postId) ?: throw PostNotFoundException()
        val like = likeRepository.save(Like(post, likeCreateDto.createdBy))
        return like.id
    }

    fun countLike(postId: Long): Long {
        return likeRepository.countByPostId(postId)
    }
}
