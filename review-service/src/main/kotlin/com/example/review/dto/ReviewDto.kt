package com.example.review.dto

import com.example.review.domain.Review

data class ReviewWriteModel(
    val productId: Long,
    val author: String,
    val content: String
) {

    fun toEntity() = Review(
        this.productId,
        this.author,
        this.content
    )
}

data class ReviewWriteRequest(
    val productId: Long,
    val author: String,
    val content: String
) {

    fun toModel() = ReviewWriteModel(productId, author, content)
}


data class ReviewSearchCommand(
    val productId: Long?,
    val author: String?
)
