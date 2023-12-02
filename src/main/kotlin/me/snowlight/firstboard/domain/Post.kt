package me.snowlight.firstboard.domain

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Lob
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import me.snowlight.firstboard.exception.PostNotUpdatableException
import me.snowlight.firstboard.service.dto.PostUpdateDto

@Entity
@Table
class Post(
    createdBy: String,
    title: String,
    content: String,
): BaseEntity(
    createdBy,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    var id: Long = 0
    @Column(name = "title")
    var title: String = title
        protected set
    @Lob
    @Column(name = "content")
    var content: String = content
        protected set

    @OneToMany(mappedBy = "post", orphanRemoval = true, cascade = [CascadeType.ALL])
    var comments: MutableList<Comment> = mutableListOf()
        protected set

    fun update(postUpdateDto: PostUpdateDto) {
        if (postUpdateDto.updatedBy != this.createdBy) {
            throw PostNotUpdatableException()
        }

        this.title = postUpdateDto.title
        this.content = postUpdateDto.content

        super.updatedBy(postUpdateDto.updatedBy)
    }
}
