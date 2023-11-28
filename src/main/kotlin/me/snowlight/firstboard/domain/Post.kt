package me.snowlight.firstboard.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Lob
import jakarta.persistence.Table

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
    val id: Long = 0
    @Column(name = "title")
    var title: String = title
        protected set
    @Lob
    @Column(name = "content")
    var content: String = content
        protected set
}
