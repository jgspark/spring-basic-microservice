package com.example.composite.domain

import com.example.composite.dto.model.SingleReviewModel
import com.example.composite.infra.repository.ReviewClient
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.anyLong
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
class ReviewReaderTest {

    lateinit var reader: ReviewReader

    @Mock
    lateinit var client: ReviewClient

    @BeforeEach
    fun init() {
        reader = ReviewReader(client)
    }

    @Test
    @Throws(Exception::class)
    fun `단건_리뷰_조회_성공`() {


        //given

        val findId = 1L

        val mock = ReviewMock.getSingleReviewMock()

        `when`(client.getReviewOne(anyLong())).thenReturn(mock)

        //when

        val entity = reader.getOne(findId)

        //then

        verify(client, times(1)).getReviewOne(anyLong())

        assertEquals(entity, mock)
        assertEquals(entity.id, mock.id)
        assertEquals(entity.productId, mock.productId)
        assertEquals(entity.author, mock.author)
        assertEquals(entity.content, mock.content)
        assertEquals(entity.createdAt, mock.createdAt)
        assertEquals(entity.updatedAt, mock.updatedAt)
    }
}

object ReviewMock {


    fun getSingleReviewMock(): SingleReviewModel = SingleReviewModel(
        1L,
        1L,
        "테스터",
        "이 리뷰는 테스트 입니다.",
        LocalDateTime.of(2020, 10, 11, 10, 30),
        LocalDateTime.of(2020, 10, 11, 10, 30)
    )
}
