package com.example.review.web

import com.example.review.dto.ReviewWriteRequest
import com.example.review.service.ReviewService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class ReviewController(
    private val reviewService: ReviewService
) {

    @PostMapping("review")
    @ResponseStatus(HttpStatus.CREATED)
    fun created(@RequestBody request: ReviewWriteRequest): Long =
        reviewService.writeOne(request.toModel())
}
