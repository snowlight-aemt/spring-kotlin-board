package me.snowlight.firstboard.service

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import me.snowlight.firstboard.domain.Post
import me.snowlight.firstboard.repository.LikeRepository
import me.snowlight.firstboard.repository.PostRepository
import me.snowlight.firstboard.service.dto.LikeCreateDto
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull

@SpringBootTest
class LikeServiceTest(
    val postRepository: PostRepository,
    val likeService: LikeService,
    val likeRepository: LikeRepository,
)
: BehaviorSpec({
    given("좋아요 생성 시") {
        val saved = postRepository.save(
            Post(
                title = "제목",
                content = "내용",
                createdBy = "글쓴이",
            )
        )
        When("게시글에 좋아요를 추가할 때") {
            val likeId = likeService.createLike(saved.id, LikeCreateDto("유저1"))

            then("좋아요가 정상적으로 생성되야 한다.") {
                val like = likeRepository.findByIdOrNull(likeId)
                like shouldNotBe null
                like?.createdBy shouldBe "유저1"
            }
        }
    }
})
