package com.example.review.service

import com.example.app.exception.NotFoundDataException
import com.example.review.app.exception.ExceptionMessage
import com.example.review.domain.Review
import com.example.review.domain.ReviewReader
import com.example.review.domain.ReviewWriter
import com.example.review.dto.ReviewWriteModel
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface ReviewService {

    fun writeOne(model: ReviewWriteModel): Long

    fun getOne(id: Long?): Review

    fun getByProductId(productId: Long?): Collection<Review>

    fun getByAuthor(author: String?): Collection<Review>
}

@Service
@Transactional(readOnly = true)
open class ReviewServiceImpl(
    private val reviewWriter: ReviewWriter, private val reviewReader: ReviewReader
) : ReviewService {

    @Transactional
    override fun writeOne(model: ReviewWriteModel): Long = reviewWriter.write(model.toEntity()).id.run {
        check(this == 0L) {
            throw NotFoundDataException(ExceptionMessage.REVIEW_NOT_SAVE)
        }
        this
    }

    override fun getOne(id: Long?): Review =
        checkNotNull(id) {
            ExceptionMessage.ID_NOT_NUll
        }.run {
            reviewReader.findOne(this)
        }

    override fun getByProductId(productId: Long?): Collection<Review> =
        checkNotNull(productId) {
            ExceptionMessage.PRODUCT_ID_NOT_NULL
        }.run {
            reviewReader.findByProductId(this)
        }

    override fun getByAuthor(author: String?): Collection<Review> =
        checkNotNull(author) {
            ExceptionMessage.AUTHOR_NOT_NULL
        }.run {
            reviewReader.findByAuthor(this)
        }

}
