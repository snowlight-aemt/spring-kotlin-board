package me.snowlight.firstboard.service

import me.snowlight.firstboard.domain.Like
import me.snowlight.firstboard.exception.PostNotFoundException
import me.snowlight.firstboard.repository.LikeRepository
import me.snowlight.firstboard.repository.PostRepository
import me.snowlight.firstboard.service.dto.LikeCreateDto
import me.snowlight.firstboard.util.RedisUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class LikeService(
    private val likeRepository: LikeRepository,
    private val postRepository: PostRepository,
    private val redisUtil: RedisUtil,
) {
    fun createLike(postId: Long, likeCreateDto: LikeCreateDto): Long {
        val post = postRepository.findByIdOrNull(postId) ?: throw PostNotFoundException()
        val like = likeRepository.save(Like(post, likeCreateDto.createdBy))

        redisUtil.increment(redisUtil.getLikeCountKey(postId))

        return like.id
    }

    fun countLike(postId: Long): Long {
        redisUtil.getCount(redisUtil.getLikeCountKey(postId))?.let { return it }

        with(likeRepository.countByPostId(postId)) {
            redisUtil.setDate(redisUtil.getLikeCountKey(postId), this)
            return this
        }
    }
}
