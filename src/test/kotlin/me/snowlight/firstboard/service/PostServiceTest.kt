package me.snowlight.firstboard.service

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.BeforeSpec
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.longs.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.string.shouldContain
import me.snowlight.firstboard.domain.Post
import me.snowlight.firstboard.exception.PostNotDeletableException
import me.snowlight.firstboard.exception.PostNotUpdatableException
import me.snowlight.firstboard.exception.PostNotFoundException
import me.snowlight.firstboard.repository.PostRepository
import me.snowlight.firstboard.service.dto.PostCreateDto
import me.snowlight.firstboard.service.dto.PostDeleteDto
import me.snowlight.firstboard.service.dto.PostSearchResponseDto
import me.snowlight.firstboard.service.dto.PostUpdateDto
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull

@SpringBootTest
class PostServiceTest(
    postService: PostService,
    postRepository: PostRepository,
): BehaviorSpec({
    beforeSpec {
        postRepository.saveAll(
            listOf(
                Post(title = "제목1", content = "내용", createdBy = "글쓴이"),
                Post(title = "제목2", content = "내용", createdBy = "글쓴이"),
                Post(title = "제목3", content = "내용", createdBy = "글쓴이"),
                Post(title = "제목4", content = "내용", createdBy = "글쓴이"),
                Post(title = "제목5", content = "내용", createdBy = "글쓴이"),
                Post(title = "제목6", content = "내용", createdBy = "글쓴이"),
                Post(title = "제목7", content = "내용", createdBy = "글쓴이"),
                Post(title = "제목8", content = "내용", createdBy = "글쓴이"),
                Post(title = "제목9", content = "내용", createdBy = "글쓴이"),
                Post(title = "제목10", content = "내용", createdBy = "글쓴이"),
                Post(title = "제목11", content = "내용", createdBy = "글쓴이"),
            )
        )
    }

    given("게시글 생성 시") {
        When("게시글 인풋이 정상적으로 들어오면") {
            val postId = postService.createPost(
                PostCreateDto(
                title= "제목",
                content= "내용",
                createdBy= "글쓴이",
            )
            )

            then("게시글 정상적으로 생성됨을 확인한다.") {
                postId shouldBeGreaterThan 0L
                val post = postRepository.findByIdOrNull(postId)
                post shouldNotBe  null
                post?.title shouldBe "제목"
                post?.content shouldBe "내용"
                post?.createdBy shouldBe "글쓴이"
            }
        }
    }

    given("게시글 수정 시") {
        val saved = postRepository.save(Post(title= "제목", content= "내용", createdBy= "글쓴이"))
        When("게시글 수정 인풋이 정상적으로 들어오면") {
            val updatedId = postService.updatePost(saved.id, PostUpdateDto(
                title= "수정 제목",
                content= "수정 내용",
                updatedBy= "글쓴이",
            )
            )

            then("게시글이 정상적으로 수정됨을 확인한다.") {
                updatedId shouldNotBe null
                val post = postRepository.findByIdOrNull(saved.id)
                post shouldNotBe  null
                post?.title shouldBe "수정 제목"
                post?.content shouldBe "수정 내용"
            }
        }
        When("게시글이 없을때") {
            then("게시글을 찾을 수 없다라는 예외가 발생한다.") {
                shouldThrow<PostNotFoundException> {
                    postService.updatePost(99999L, PostUpdateDto(
                        title= "수정 제목",
                        content= "수정 내용",
                        updatedBy= "수정 글쓴이",
                    )
                    )
                }
            }
        }
        When("게시글 작성자가 아닐때") {
            then("수정할 수 없는 게시글 입니다. 예외가 발생한다.") {
                shouldThrow<PostNotUpdatableException> {
                    postService.updatePost(saved.id, PostUpdateDto(
                        title= "수정 제목",
                        content= "수정 내용",
                        updatedBy= "수정 글쓴이1",
                    )
                    )
                }
            }
        }
    }

    given("게시글 삭제 시") {
        val saved = postRepository.save(Post(title= "제목", content= "내용", createdBy= "글쓴이"))
        When("게시글 삭제가 정상적일때") {
            val deletedId = postService.deletePost(saved.id, PostDeleteDto(
                updatedBy= "글쓴이"
            )
            )

            then("게시글 삭제를 확인한다.") {
                deletedId shouldBe saved.id
                postRepository.findByIdOrNull(deletedId) shouldBe null
            }
        }
        When("게시글을 삭제할 수 없습니다, 예외가 발생한다.") {
            val saved2 = postRepository.save(Post(title= "제목", content= "내용", createdBy= "글쓴이"))
            then("게시글 삭제를 확인한다.") {
                shouldThrow<PostNotDeletableException> {
                    postService.deletePost(saved2.id, PostDeleteDto(
                        updatedBy= "없는 글쓴이"
                    )
                    )
                }
            }
        }
    }

    given("게시글 상세 조회 시") {
        val saved = postRepository.save(Post(title = "제목", content = "내용", createdBy = "글쓴이"))
        When("게시글 조회 인풋을 정상적으로 할 때") {
            val postDetailResponseDto = postService.getPost(saved.id)
            then("게시글 상세 정보가 조회된다.") {
                postDetailResponseDto shouldNotBe null
                postDetailResponseDto.id shouldBe saved.id
                postDetailResponseDto.title shouldBe "제목"
                postDetailResponseDto.content shouldBe "내용"
                postDetailResponseDto.createdBy shouldBe "글쓴이"
                postDetailResponseDto.createdDate shouldBe saved.createdDate
            }
        }
        When("게시글을 찾을 수 없을때") {
            then("게시글을 찾을 수 없다라는 예외가 발생한다.") {
                shouldThrow<PostNotFoundException> {
                    postService.getPost(9999L)
                }
            }
        }
    }

    given("게시글 조회 시") {
        When("게시글 목록 조회할 때") {
            val page: Page<PostSearchResponseDto> = postService.getPageBy(PageRequest.of(0, 5))
            then("게시글 목록을 확인할 수 있다.") {
                page.number shouldBe 0
                page.size shouldBe 5
                page.content.size shouldBe 5
                page.content[0].title shouldContain "제목"
                page.content[0].createdBy shouldContain "글쓴이"
                page.content[1].title shouldContain "제목2"
                page.content[1].createdBy shouldContain "글쓴이"
            }
        }
    }
})
