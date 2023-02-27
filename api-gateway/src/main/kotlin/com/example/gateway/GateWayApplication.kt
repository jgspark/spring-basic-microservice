package com.example.gateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class GateWayApplication

fun main(args: Array<String>) {
    runApplication<GateWayApplication>(*args)
}
