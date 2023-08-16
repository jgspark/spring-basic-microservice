package com.example.composite.infra.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "client")
data class ClientProperties(
    val review: Review
)

data class Review(
    val baseUrl: String
)
