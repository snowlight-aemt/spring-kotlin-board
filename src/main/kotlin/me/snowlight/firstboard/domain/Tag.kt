package me.snowlight.firstboard.domain

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table
class Tag(
    name: String,
    createdBy: String,
    post: Post,
) : BaseEntity(createdBy) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    val id: Long = 0L

    @Column(name = "name")
    val name: String = name

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "post_id")
    val post: Post = post
}
