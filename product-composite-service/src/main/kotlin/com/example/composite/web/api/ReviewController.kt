package com.example.composite.web.api

import com.example.composite.domain.ReviewReader
import com.example.composite.dto.model.SingleReviewModel
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ReviewController(
    private val reader: ReviewReader
) {


    @GetMapping("review/{id}")
    fun getReview(
        @PathVariable id: Long
    ): SingleReviewModel = reader.getOne(id)
}
