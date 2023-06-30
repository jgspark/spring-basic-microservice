package com.example.review.domain

import com.example.app.exception.NotFoundDataException
import com.example.review.app.exception.ExceptionMessage
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
open class ReviewReaderTest {

    @Autowired
    lateinit var reviewRepository: ReviewRepository

    @Autowired
    lateinit var reviewReader: ReviewReader

    @Test
    @Transactional
    @Throws(Exception::class)
    open fun `단일 리뷰 조회`() {

        //given
        val review = Review(0L, 1L, "리뷰인플런서", "상품을 사용을 했지만, 나쁘지 않았습니다", null, null)
        reviewRepository.save(review)

        //when
        val entity = reviewReader.findOne(review.id)

        //then
        assertEquals(review, entity)
        assertEquals(review.author, entity.author)
        assertEquals(review.content, entity.content)
        assertEquals(review.createdAt, entity.createdAt)
        assertEquals(review.updatedAt, entity.updatedAt)
    }

    @Test
    @Transactional
    @Throws(Exception::class)
    open fun `조회시 만약 데이터가 없다면`() {

        //given
        val id = 1000L

        //when

        val e = org.junit.jupiter.api.assertThrows<NotFoundDataException> {
            reviewReader.findOne(id)
        }

        //then
        assertEquals(e.message, ExceptionMessage.REVIEW_SELECT_NOT_FOUND)
    }
}
