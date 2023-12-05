package me.snowlight.firstboard.service

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import me.snowlight.firstboard.domain.Comment
import me.snowlight.firstboard.domain.Post
import me.snowlight.firstboard.exception.CommentNotDeletableException
import me.snowlight.firstboard.exception.CommentNotFoundException
import me.snowlight.firstboard.exception.CommentNotUpdatableException
import me.snowlight.firstboard.exception.PostNotFoundException
import me.snowlight.firstboard.repository.CommentRepository
import me.snowlight.firstboard.repository.PostRepository
import me.snowlight.firstboard.service.dto.CommentCreateDto
import me.snowlight.firstboard.service.dto.CommentDeleteDto
import me.snowlight.firstboard.service.dto.CommentUpdateDto
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull

@SpringBootTest
class CommentServiceTest(
    commentRepository: CommentRepository,
    commentService: CommentService,
    postRepository: PostRepository,
) : BehaviorSpec({
    given("댓글 생성 시") {
        val postSaved = postRepository.save(Post(title = "제목", content = "내용", createdBy = "유저1"))
        When("댓글에 정상 인풋이 입력될 때") {
            val commentId = commentService.createComment(
                postSaved.id,
                CommentCreateDto(
                    content = "댓글 내용",
                    createdBy = "유저2"
                )
            )
            then("댓글이 정상적으로 생성되는지 확인") {
                commentId shouldNotBe null
                val comment = commentRepository.findByIdOrNull(commentId)
                comment shouldNotBe null
                comment?.content shouldBe "댓글 내용"
                comment?.createdBy shouldBe "유저2"
            }
        }

        When("댓글에 대한 게시글이 없을 때") {
            then("게시글을 찾을 수 없어서 예외가 발생한다.") {
                shouldThrow<PostNotFoundException> {
                    commentService.createComment(
                        99999L,
                        CommentCreateDto(
                            content = "댓글 내용",
                            createdBy = "유저2"
                        )
                    )
                }
            }
        }
    }

    given("댓글 수정 시") {
        val postSaved = postRepository.save(Post(title = "제목", content = "내용", createdBy = "유저1"))
        When("댓글에 정상 인풋을 입력했을 때") {
            val commentSaved = commentRepository.save(Comment(content = "내용", createdBy = "유저2", post = postSaved))
            val commentId = commentService.updateComment(
                commentSaved.id,
                CommentUpdateDto(content = "수정 내용", updatedBy = "유저2")
            )
            then("댓글이 정상적으로 수정되었는지 확인한다.") {
                commentId shouldNotBe null
                val commentUpdated = commentRepository.findByIdOrNull(commentId)
                commentUpdated shouldNotBe null
                commentUpdated?.content shouldBe "수정 내용"
                commentUpdated?.createdBy shouldBe "유저2"
            }
        }

        When("댓글이 없는 경우") {
            then("댓글을 찾을 수 없다는 예외가 발생한다.") {
                shouldThrow<CommentNotFoundException> {
                    commentService.updateComment(
                        99999L,
                        CommentUpdateDto(content = "수정 내용", updatedBy = "유저2")
                    )
                }
            }
        }

        When("댓글 수정을 요청하는 유저가 동일하지 않을 때") {
            val commentSaved = commentRepository.save(Comment(content = "내용", createdBy = "유저2", post = postSaved))
            then("댓글을 수정할 수 없다는 예외가 발생한다.") {
                shouldThrow<CommentNotUpdatableException> {
                    commentService.updateComment(
                        commentSaved.id,
                        CommentUpdateDto(content = "수정 내용", updatedBy = "유저1")
                    )
                }
            }
        }
    }

    given("댓글 삭제 시") {
        val postSaved = postRepository.save(Post(title = "제목", content = "내용", createdBy = "유저1"))
        val commentSaved = commentRepository.save(Comment(content = "내용", createdBy = "유저2", post = postSaved))
        When("댓글에 정보가 정상인 경우") {
            val commentIdDeleted = commentService.deleteComment(commentSaved.id, CommentDeleteDto("유저2"))
            then("댓글이 정상적으로 삭제되어는 확인한다.") {
                commentIdDeleted shouldNotBe null
                commentIdDeleted shouldBe commentSaved.id
                val commentDeleted = commentRepository.findByIdOrNull(commentIdDeleted)
                commentDeleted shouldBe null
            }
        }

        When("댓글을 찾을 수 없는 경우") {
            then("댓글을 찾을 수 없습니다 예외가 발생한다.") {
                shouldThrow<CommentNotFoundException> {
                    commentService.deleteComment(
                        99999L,
                        CommentDeleteDto("유저2")
                    )
                }
            }
        }

        When("댓글을 생성한 작성자와 삭제하는 유저가 동일하지 않는 경우") {
            val commentSaved2 = commentRepository.save(Comment(content = "내용", createdBy = "유저2", post = postSaved))
            then("댓글을 삭제할 수 없습니다. 에외가 발생한다.") {
                shouldThrow<CommentNotDeletableException> {
                    commentService.deleteComment(
                        commentSaved2.id,
                        CommentDeleteDto("유저3")
                    )
                }
            }
        }
    }
})
