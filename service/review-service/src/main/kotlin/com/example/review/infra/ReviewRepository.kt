package com.example.review.infra

import com.example.review.domain.Review
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * todo : 만약 리뷰의 데이터가 N 개라면?
 */
@Repository
interface ReviewRepository : JpaRepository<Review, Long> {

    fun findByProductId(productId: Long): Collection<Review>

    fun findByAuthor(author: String): Collection<Review>
}
