package com.example.review.service

import com.example.review.app.exception.ExceptionMessage
import com.example.review.domain.ReviewReader
import com.example.review.domain.ReviewWriter
import com.example.review.dto.ReviewSearchCommand
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class ReviewServiceTest {

    @InjectMocks
    lateinit var reviewService: ReviewServiceImpl

    @Mock
    lateinit var reviewWriter: ReviewWriter

    @Mock
    lateinit var reviewReader: ReviewReader

    @BeforeEach
    fun init() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    @Throws(Exception::class)
    fun `리뷰 조회 시 command 가 productId 와 author 가 null 일 떄`() {

        //given
        val reviewSearchCommand = ReviewSearchCommand(null, null)

        //when
        val e = org.junit.jupiter.api.assertThrows<IllegalStateException> {
            reviewService.getAllBy(reviewSearchCommand)
        }

        //then
        assertEquals(ExceptionMessage.ARGS_SEARCH_COMMAND_NOT_NULL, e.message)
    }
}
