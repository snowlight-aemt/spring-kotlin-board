package me.snowlight.firstboard.service

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import me.snowlight.firstboard.domain.Post
import me.snowlight.firstboard.exception.PostNotFoundException
import me.snowlight.firstboard.repository.CommentRepository
import me.snowlight.firstboard.repository.PostRepository
import me.snowlight.firstboard.service.dto.CommentCreateDto
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull

@SpringBootTest
class CommentServiceTest(
    commentRepository: CommentRepository,
    commentService: CommentService,
    postRepository: PostRepository,
): BehaviorSpec({
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
})
