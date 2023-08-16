package com.example.review.domain

import com.example.review.infra.ReviewRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@ExtendWith(SpringExtension::class)
open class ReviewWriterTest {

    @Autowired
    lateinit var reviewRepository: ReviewRepository

    @Autowired
    lateinit var reviewWriter: ReviewWriter

    @Test
    @Transactional
    @Throws(Exception::class)
    open fun `리뷰 저장 테스트`() {

        //given
        val review = Review(0L, 1L, "구매자1", "아주 좋은 상품입니다", null, null)

        //when
        val entity = reviewWriter.write(review)

        //then
        val findEntity = reviewRepository.findById(entity.id).orElseThrow()
        assertEquals(entity, findEntity)
        assertEquals(entity.productId, findEntity.productId)
        assertEquals(entity.author, findEntity.author)
        assertEquals(entity.createdAt, findEntity.createdAt)
        assertEquals(entity.updatedAt, findEntity.updatedAt)
    }

}
