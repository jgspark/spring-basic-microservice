package com.example.review.infra

import com.example.review.app.exception.ExceptionMessage
import com.example.review.domain.Review
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.dao.InvalidDataAccessApiUsageException
import org.springframework.transaction.annotation.Transactional


@DataJpaTest
@DisplayName("리뷰 레파지토리 에서")
open class ReviewRepositoryTest {

    @Autowired
    lateinit var reviewRepository: ReviewRepository

    @Nested
    open inner class `저장 메소드는` {

        @Test
        @Transactional
        @Throws(Exception::class)
        open fun `Product id 는 0L 로 저장 할 수 없다`() {

            //given

            val mock = Review(0L, "작성자", "리뷰를 남긴다..")

            //when

            val e = assertThrows<InvalidDataAccessApiUsageException> {
                reviewRepository.save(mock)
            }

            //then
            assertEquals(e.message, ExceptionMessage.REVIEW_SAVE_PRODUCT_ID)
        }

        @Test
        @Transactional
        @Throws(Exception::class)
        open fun `정상적으로 동작한다`() {

            //given
            val mock = Review(1L, "작성자", "리뷰를 남긴다..")

            //when
            val entity = reviewRepository.save(mock)

            //then
            assertEquals(entity.productId, mock.productId)
            assertEquals(entity.author, mock.author)
            assertEquals(entity.content, mock.content)
            assertEquals(entity, mock)
        }
    }
}
