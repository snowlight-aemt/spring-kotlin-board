package me.snowlight.firstboard.domain

import jakarta.persistence.MappedSuperclass
import java.time.LocalDateTime

@MappedSuperclass
abstract class BaseEntity (
    createdBy: String,
) {
    val createdBy: String = createdBy
    val createdDate: LocalDateTime = LocalDateTime.now()
    var updatedBy: String? = null
        protected set
    var updatedDate: LocalDateTime? = null
        protected set

    fun updatedBy(updatedBy: String) {
        this.updatedBy = updatedBy
        this.updatedDate = LocalDateTime.now()
    }
}
