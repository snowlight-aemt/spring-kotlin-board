package me.snowlight.firstboard.service

import me.snowlight.firstboard.domain.Comment
import me.snowlight.firstboard.repository.CommentRepository
import me.snowlight.firstboard.service.dto.CommentCreateDto
import me.snowlight.firstboard.service.dto.toEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CommentService(
    val commentRepository: CommentRepository,
    val postService: PostService,
) {
    fun createComment(postId: Long, commentCreateDto: CommentCreateDto): Long {
        // TODO toDomain : 확장 함수... 구현 위치가....
        val post = postService.getPost(postId).toEntity()

        val comment = commentRepository.save(commentCreateDto.toEntity(post))
        return comment.id
    }
}
