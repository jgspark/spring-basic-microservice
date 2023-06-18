package com.example.review.domain

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
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?,
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
    }

    @PreUpdate
    fun preUpdate(){
        check(productId != 0L) {
            ExceptionMessage.REVIEW_SAVE_PRODUCT_ID
        }
    }
}

@Component
open class ReviewWriter(
    private val reviewRepository: ReviewRepository
) {

    @Transactional
    open fun write(entity: Review) = reviewRepository.save(entity)
}
