package com.example.composite.domain

import com.example.composite.dto.model.SingleReviewModel
import com.example.composite.infra.repository.ReviewClient
import org.springframework.stereotype.Component


@Component
class ReviewReader(
    private val reviewClient: ReviewClient
) {

    fun getOne(id: Long): SingleReviewModel =
        reviewClient.getReviewOne(id)
}
