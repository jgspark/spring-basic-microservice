package com.example.composite.dto.model

import java.time.LocalDateTime


data class SingleReviewModel(
    var id: Long?,
    val productId: Long,
    var author: String,
    var content: String,
    var createdAt: LocalDateTime?,
    var updatedAt: LocalDateTime?,
) {

    constructor() : this(null, 0L, "", "", null, null)

}
