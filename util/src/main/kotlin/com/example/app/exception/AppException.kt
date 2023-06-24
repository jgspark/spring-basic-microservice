package com.example.app.exception

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * 어플리케이션내의 글로벌 핸들러
 */

@RestControllerAdvice
class GlobalExceptionHandler {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @ExceptionHandler(NotFoundDataException::class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun handler(e: NotFoundDataException): AppExceptionMessage =
        AppExceptionMessage(HttpStatus.NO_CONTENT.value(), e.message).let {
            logger.warn("Not Found Data : {}", e.data)
            it
        }

}

data class AppExceptionMessage(
    val code: Int, val message: String?
)

class NotFoundDataException(
    val data: Any?,
    override val message: String?,
) : RuntimeException(message) {

    constructor() : this(null, null)

    constructor(message: String?) : this(null, message)
}
