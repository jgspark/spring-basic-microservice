package com.example.app.exception

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * 어플리케이션내의 글로벌 핸들러
 */

private val logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

@RestControllerAdvice
class GlobalExceptionHandler {


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun handler(e: Exception) =
        AppExceptionMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.message)
            .run {
                logger.error(e.message, e)
                this
            }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler(NotFoundDataException::class)
    fun handler(e: NotFoundDataException): AppExceptionMessage =
        AppExceptionMessage(HttpStatus.NO_CONTENT.value(), e.message)
            .run {
                logger.warn("Not Found Data : {}", e.data)
                this
            }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(
        value = [
            IllegalStateException::class
        ]
    )
    fun handler(e: IllegalStateException) =
        AppExceptionMessage(HttpStatus.BAD_REQUEST.value(), e.message)
            .run {
                logger.error(e.message, e)
                this
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
