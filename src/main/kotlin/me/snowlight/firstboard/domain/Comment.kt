package me.snowlight.firstboard.domain

import jakarta.persistence.Column
import jakarta.persistence.ConstraintMode
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ForeignKey
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import me.snowlight.firstboard.exception.CommentNotUpdatableException
import me.snowlight.firstboard.service.dto.CommentUpdateDto

@Entity
@Table(indexes = [ Index(name = "idx_post_id", columnList = "post_id") ])
class Comment(
    content: String,
    createdBy: String,
    post: Post,
) : BaseEntity(
    createdBy = createdBy
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    val id: Long = 0

    @Column(name = "content")
    var content: String = content
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    val post: Post = post

    fun update(commentUpdateDto: CommentUpdateDto) {
        if (commentUpdateDto.updatedBy != createdBy) {
            throw CommentNotUpdatableException()
        }

        content = commentUpdateDto.content

        super.updatedBy(commentUpdateDto.updatedBy)
    }
}
