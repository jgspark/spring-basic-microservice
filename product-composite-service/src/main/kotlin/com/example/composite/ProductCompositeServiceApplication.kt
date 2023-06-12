package com.example.composite

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class ProductCompositeServiceApplication

fun main(args: Array<String>) {
    runApplication<ProductCompositeServiceApplication>(*args)
}
