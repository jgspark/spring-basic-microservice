package com.example.composite.core.exception

import com.example.app.exception.AppExceptionMessage
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.reactive.function.client.WebClientRequestException


private val logger = LoggerFactory.getLogger(AppExceptionHandler::class.java)

@RestControllerAdvice
class AppExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = [WebClientRequestException::class])
    fun handler(e: Exception) = AppExceptionMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.message).run {
        logger.error(e.message, e)
        this
    }

}
