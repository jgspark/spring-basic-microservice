package com.example.exception

class InvalidInputException (
    val data: Any?,
    override val message: String?,
) : RuntimeException(message) {

    constructor() : this(null, null)

    constructor(message: String?) : this(null, message)
}

