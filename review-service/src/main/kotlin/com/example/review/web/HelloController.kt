package com.example.review.web

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("hello")
class HelloController {

    @GetMapping("/message")
    fun getMessage() = "Hello"
}
