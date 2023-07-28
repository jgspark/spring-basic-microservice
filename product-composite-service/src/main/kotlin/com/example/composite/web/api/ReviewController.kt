package com.example.composite.web.api

import com.example.composite.dto.model.SingleReviewModel
import com.example.composite.infra.repository.ReviewClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ReviewController(
    private val reviewClient: ReviewClient
) {


    @GetMapping("review/{id}")
    fun getReview(
        @PathVariable id: Long
    ): SingleReviewModel = reviewClient.getReviewOne(id)
}
