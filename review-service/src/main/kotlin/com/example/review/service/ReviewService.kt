package com.example.review.service

import com.example.app.exception.NotFoundDataException
import com.example.review.app.exception.ExceptionMessage
import com.example.review.domain.ReviewWriter
import com.example.review.dto.ReviewWriteModel
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException

interface ReviewService {
    fun writeOne(model: ReviewWriteModel): Long
}

@Service
open class ReviewServiceImpl(
    private val reviewWriter: ReviewWriter
) : ReviewService {

    @Transactional
    override fun writeOne(model: ReviewWriteModel): Long = reviewWriter.write(model.toEntity()).id
        .run {
            check(this == 0L) {
                throw NotFoundDataException(ExceptionMessage.REVIEW_NOT_SAVE)
            }
            this
        }

}
