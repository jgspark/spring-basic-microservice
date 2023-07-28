package com.example.review.service

import com.example.app.exception.NotFoundDataException
import com.example.review.app.exception.ExceptionMessage
import com.example.review.domain.Review
import com.example.review.domain.ReviewReader
import com.example.review.domain.ReviewWriter
import com.example.review.dto.ReviewMergeModel
import com.example.review.dto.ReviewSearchCommand
import com.example.review.dto.ReviewWriteModel
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface ReviewService {

    fun writeOne(model: ReviewWriteModel): Long

    fun mergeOne(model: ReviewMergeModel): Review

    fun getOne(id: Long?): Review

    fun getAllBy(command: ReviewSearchCommand): Collection<Review>
}

@Service
@Transactional(readOnly = true)
open class ReviewServiceImpl(
    private val reviewWriter: ReviewWriter,
    private val reviewReader: ReviewReader
) : ReviewService {

    @Transactional
    override fun writeOne(model: ReviewWriteModel): Long = reviewWriter.write(model.toEntity()).id
        ?: throw NotFoundDataException(ExceptionMessage.REVIEW_NOT_SAVE)

    @Transactional
    override fun mergeOne(model: ReviewMergeModel): Review = reviewWriter.update(model.toEntity())

    override fun getOne(id: Long?): Review =
        checkNotNull(id) {
            ExceptionMessage.ID_NOT_NUll
        }.run {
            reviewReader.findOne(this)
        }

    override fun getAllBy(command: ReviewSearchCommand): Collection<Review> {

        check(!(command.productId == null && command.author == null)) {
            ExceptionMessage.ARGS_SEARCH_COMMAND_NOT_NULL
        }

        check(!(command.productId != null && command.author != null)) {
            ExceptionMessage.ARGS_SEARCH_COMMAND_NOT_NULL
        }

        return if (command.productId != null) {
            reviewReader.findByProductId(command.productId)
        } else {
            reviewReader.findByAuthor(command.author!!)
        }
    }
}
