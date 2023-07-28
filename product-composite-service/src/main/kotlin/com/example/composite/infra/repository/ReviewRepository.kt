package com.example.composite.infra.repository

import com.example.composite.dto.model.SingleReviewModel
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.HttpExchange

interface ReviewRepository

@HttpExchange("")
interface ReviewClient {

    @GetExchange("review/{id}")
    fun getReviewOne(@PathVariable id: Long): SingleReviewModel
}
