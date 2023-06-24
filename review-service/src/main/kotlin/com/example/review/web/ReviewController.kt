package com.example.review.web

import com.example.review.dto.ReviewMergeRequest
import com.example.review.dto.ReviewSearchCommand
import com.example.review.dto.ReviewWriteRequest
import com.example.review.service.ReviewService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
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
    fun create(@RequestBody request: ReviewWriteRequest): Long =
        reviewService.writeOne(request.toModel())

    @PatchMapping("review/{id}")
    fun merge(@PathVariable id: Long, @RequestBody request: ReviewMergeRequest) =
        reviewService.mergeOne(request.toModel(id))

    @GetMapping("review/{id}")
    fun showByOne(@PathVariable id: Long?) = reviewService.getOne(id)

    @GetMapping("reviews")
    fun showByAuthor(command: ReviewSearchCommand) = reviewService.getAllBy(command)

}
