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
        val entity = reviewReader.findOne(review.id!!)

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

    @Test
    @Transactional
    @Throws(Exception::class)
    open fun `주문 아이디별 리뷰 조회`() {

        //given
        val reviews = mutableListOf(
            Review(0L, 1L, "리뷰인플런서", "상품을 사용을 했지만, 나쁘지 않았습니다", null, null),
            Review(0L, 1L, "리뷰인플런서", "상품을 사용을 했지만, 나쁘지 않았습니다", null, null),
            Review(0L, 1L, "리뷰인플런서", "상품을 사용을 했지만, 나쁘지 않았습니다", null, null),
            Review(0L, 1L, "리뷰인플런서", "상품을 사용을 했지만, 나쁘지 않았습니다", null, null),
            Review(0L, 1L, "리뷰인플런서", "상품을 사용을 했지만, 나쁘지 않았습니다", null, null),
            Review(0L, 1L, "리뷰인플런서", "상품을 사용을 했지만, 나쁘지 않았습니다", null, null),
            Review(0L, 1L, "리뷰인플런서", "상품을 사용을 했지만, 나쁘지 않았습니다", null, null),
            Review(0L, 1L, "리뷰인플런서", "상품을 사용을 했지만, 나쁘지 않았습니다", null, null),
            Review(0L, 1L, "리뷰인플런서", "상품을 사용을 했지만, 나쁘지 않았습니다", null, null),
            Review(0L, 1L, "리뷰인플런서", "상품을 사용을 했지만, 나쁘지 않았습니다", null, null),
            Review(0L, 1L, "리뷰인플런서", "상품을 사용을 했지만, 나쁘지 않았습니다", null, null),
            Review(0L, 1L, "리뷰인플런서", "상품을 사용을 했지만, 나쁘지 않았습니다", null, null)
        )

        reviewRepository.saveAll(reviews)

        val findProductId = 1L

        //when

        val entities = reviewReader.findByProductId(findProductId)

        //then

        assertArrayEquals(reviews.toTypedArray(), entities.toTypedArray())
        assertEquals(reviews.size, entities.size)
    }

    @Test
    @Transactional
    @Throws(Exception::class)
    open fun `상품에 대한 리뷰뷰조회시 빈값이 반환이 되는가?`() {

        //given

        val findProductId = 1L

        //when

        val entities = reviewReader.findByProductId(findProductId)

        //then

        assertTrue(entities.isEmpty())
    }

    @Test
    @Transactional
    @Throws(Exception::class)
    open fun `리뷰자별 리뷰 조회`() {

        //given
        val reviews = mutableListOf(
            Review(0L, 1L, "리뷰인플런서", "상품을 사용을 했지만, 나쁘지 않았습니다", null, null),
            Review(0L, 1L, "리뷰인플런서", "상품을 사용을 했지만, 나쁘지 않았습니다", null, null),
            Review(0L, 1L, "리뷰인플런서", "상품을 사용을 했지만, 나쁘지 않았습니다", null, null),
            Review(0L, 1L, "리뷰인플런서", "상품을 사용을 했지만, 나쁘지 않았습니다", null, null),
            Review(0L, 1L, "리뷰인플런서", "상품을 사용을 했지만, 나쁘지 않았습니다", null, null),
            Review(0L, 1L, "리뷰인플런서", "상품을 사용을 했지만, 나쁘지 않았습니다", null, null),
            Review(0L, 1L, "리뷰인플런서", "상품을 사용을 했지만, 나쁘지 않았습니다", null, null),
            Review(0L, 1L, "리뷰인플런서", "상품을 사용을 했지만, 나쁘지 않았습니다", null, null),
            Review(0L, 1L, "리뷰인플런서", "상품을 사용을 했지만, 나쁘지 않았습니다", null, null),
            Review(0L, 1L, "리뷰인플런서", "상품을 사용을 했지만, 나쁘지 않았습니다", null, null),
            Review(0L, 1L, "리뷰인플런서", "상품을 사용을 했지만, 나쁘지 않았습니다", null, null),
            Review(0L, 1L, "리뷰인플런서", "상품을 사용을 했지만, 나쁘지 않았습니다", null, null)
        )

        reviewRepository.saveAll(reviews)

        val findAuthor = "리뷰인플런서"

        //when

        val entities = reviewReader.findByAuthor(findAuthor)

        //then

        assertArrayEquals(reviews.toTypedArray(), entities.toTypedArray())
        assertEquals(reviews.size, entities.size)
    }

    @Test
    @Transactional
    @Throws(Exception::class)
    open fun `리뷰자에 대한 리뷰뷰조회시 빈값이 반환이 되는가?`() {

        //given

        val findAuthor = "리뷰인플런서"


        //when

        val entities = reviewReader.findByAuthor(findAuthor)

        //then

        assertTrue(entities.isEmpty())
    }

}
