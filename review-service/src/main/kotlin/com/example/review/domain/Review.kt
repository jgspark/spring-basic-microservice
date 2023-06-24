package com.example.review.domain

import com.example.app.exception.NotFoundDataException
import com.example.review.app.exception.ExceptionMessage
import com.example.review.infra.ReviewRepository
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.PrePersist
import jakarta.persistence.PreUpdate
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Entity
data class Review(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    val id: Long,
    @Column(nullable = false)
    val productId: Long,
    @Column(nullable = false)
    val author: String,
    @Column(nullable = false)
    val content: String,
    var createdAt: LocalDateTime?,
    var updatedAt: LocalDateTime?,
) {

    constructor(productId: Long, author: String, content: String) : this(
        id = 0L,
        productId = productId,
        author = author,
        content = content,
        createdAt = null,
        updatedAt = null
    )

    constructor() : this(
        0L,
        0L,
        "",
        "",
        null,
        null
    )

    // 엔티티의 기본 스팩 정의
    @PrePersist
    fun prePersist() {

        check(productId != 0L) {
            ExceptionMessage.REVIEW_SAVE_PRODUCT_ID
        }

        this.createdAt = LocalDateTime.now()
        this.updatedAt = LocalDateTime.now()
    }

    @PreUpdate
    fun preUpdate() {

        check(productId != 0L) {
            ExceptionMessage.REVIEW_SAVE_PRODUCT_ID
        }

        this.updatedAt = LocalDateTime.now()
    }
}

@Component
open class ReviewWriter(
    private val reviewRepository: ReviewRepository
) {

    @Transactional
    open fun write(entity: Review): Review = reviewRepository.save(entity)
}


@Component
open class ReviewReader(
    private val reviewRepository: ReviewRepository
) {

    @Transactional(readOnly = true)
    open fun findOne(id: Long): Review = reviewRepository.findById(id)
        .orElseThrow {
            throw NotFoundDataException(
                id,
                ExceptionMessage.REVIEW_SELECT_NOT_FOUND
            )
        }

    @Transactional(readOnly = true)
    open fun findByProductId(productId: Long): Collection<Review> =
        reviewRepository.findByProductId(productId)

    @Transactional(readOnly = true)
    open fun findByAuthor(author: String) =
        reviewRepository.findByAuthor(author)
}
