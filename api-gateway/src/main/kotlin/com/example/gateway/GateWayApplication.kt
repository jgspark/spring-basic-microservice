package com.example.gateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.zuul.EnableZuulProxy

@EnableZuulProxy
@SpringBootApplication
open class GateWayApplication

fun main(args: Array<String>) {
    runApplication<GateWayApplication>(*args)
}
