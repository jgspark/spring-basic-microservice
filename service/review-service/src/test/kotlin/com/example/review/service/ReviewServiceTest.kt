package com.example.review.service

import com.example.review.app.exception.ExceptionMessage
import com.example.review.domain.ReviewReader
import com.example.review.domain.ReviewWriter
import com.example.review.dto.ReviewSearchCommand
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension
import java.util.stream.Stream

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

    @ParameterizedTest
    @MethodSource("getReviewSearchCommandArgs")
    @Throws(Exception::class)
    fun `리뷰 조회 시 command 가 productId 와 author 가 null 일 떄`(productId: Long?, author: String?) {

        //given
        val reviewSearchCommand = ReviewSearchCommand(productId, author)

        //when
        val e = org.junit.jupiter.api.assertThrows<IllegalStateException> {
            reviewService.getAllBy(reviewSearchCommand)
        }

        //then
        assertEquals(ExceptionMessage.ARGS_SEARCH_COMMAND_NOT_NULL, e.message)
    }

    companion object {
        @JvmStatic
        fun getReviewSearchCommandArgs(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(null, null),
                Arguments.of(1L, "테스트")
            )
        }
    }
}
