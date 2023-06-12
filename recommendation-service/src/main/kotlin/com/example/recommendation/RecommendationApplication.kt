package com.example.recommendation

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class BannerApplication

fun main(args: Array<String>) {
    runApplication<BannerApplication>(*args)
}
