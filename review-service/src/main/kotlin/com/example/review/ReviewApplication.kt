package com.example.content

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class ContentApplication

fun main(args: Array<String>) {
    runApplication<ContentApplication>(*args)
}
