package me.snowlight.firstboard.service

import me.snowlight.firstboard.exception.CommentNotDeletableException
import me.snowlight.firstboard.exception.CommentNotFoundException
import me.snowlight.firstboard.exception.PostNotFoundException
import me.snowlight.firstboard.repository.CommentRepository
import me.snowlight.firstboard.repository.PostRepository
import me.snowlight.firstboard.service.dto.CommentCreateDto
import me.snowlight.firstboard.service.dto.CommentDeleteDto
import me.snowlight.firstboard.service.dto.CommentUpdateDto
import me.snowlight.firstboard.service.dto.toEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CommentService(
    val commentRepository: CommentRepository,
    val postRepository: PostRepository,
) {
    @Transactional
    fun createComment(postId: Long, commentCreateDto: CommentCreateDto): Long {
        // TODO toDomain : 확장 함수... 구현 위치가....
        val post = postRepository.findByIdOrNull(postId) ?: throw PostNotFoundException()
        val comment = commentRepository.save(commentCreateDto.toEntity(post))
        return comment.id
    }

    @Transactional
    fun updateComment(commentId: Long, commentUpdateDto: CommentUpdateDto): Long {
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw CommentNotFoundException()
        comment.update(commentUpdateDto)

        return commentId;
    }

    @Transactional
    fun deleteComment(commentId: Long, commentDeleteDto: CommentDeleteDto): Long {
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw CommentNotFoundException()
        if (commentDeleteDto.updatedBy != comment.createdBy ) {
            throw CommentNotDeletableException();
        }

        commentRepository.delete(comment)
        return commentId
    }
}
