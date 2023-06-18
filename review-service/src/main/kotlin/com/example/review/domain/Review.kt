package com.example.review.domain

import com.example.review.infra.ReviewRepository
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Entity
data class Review(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val productId: Long?,
    val author: String?,
    val content: String?,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?,
) {

    constructor(productId: Long?, author: String?, content: String?) : this(
        id = 0L,
        productId = productId,
        author = author,
        content = content,
        createdAt = null,
        updatedAt = null
    )

    constructor() : this(
        0L,
        null,
        null,
        null,
        null,
        null
    )
}

@Component
open class ReviewWriter(
    private val reviewRepository: ReviewRepository
) {

    @Transactional
    open fun write(entity: Review) = reviewRepository.save(entity)
}
