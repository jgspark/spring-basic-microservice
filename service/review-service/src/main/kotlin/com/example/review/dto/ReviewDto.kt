package com.example.review.dto

import com.example.review.domain.Review

data class ReviewWriteModel(
    val productId: Long,
    val author: String,
    val content: String
) {

    fun toEntity(): Review =
        Review(0L, productId, author, content, null, null)
}

data class ReviewMergeModel(
    val id: Long,
    val author: String,
    val content: String
) {

    fun toEntity(): Review =
        Review(id, 0L, author, content, null, null)
}

data class ReviewWriteRequest(
    val productId: Long,
    val author: String,
    val content: String
) {

    fun toModel() = ReviewWriteModel(productId, author, content)
}

data class ReviewMergeRequest(
    val author: String,
    val content: String
) {
    fun toModel(id: Long) = ReviewMergeModel(id, author, content)
}


data class ReviewSearchCommand(
    val productId: Long?,
    val author: String?
)
