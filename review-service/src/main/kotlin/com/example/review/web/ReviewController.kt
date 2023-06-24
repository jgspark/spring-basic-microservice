package com.example.review.web

import com.example.review.dto.ReviewWriteRequest
import com.example.review.service.ReviewService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
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

    @GetMapping("review/{id}")
    fun showByOne(@PathVariable id: Long?) = reviewService.getOne(id)

    // todo : 검색 조건 thinking
    @GetMapping("reviews/product/{productId}")
    fun showByProductId(@PathVariable productId: Long?) = reviewService.getByProductId(productId)

    @GetMapping("reivews")
    fun showByAuthor(author: String?) = reviewService.getByAuthor(author)

}
