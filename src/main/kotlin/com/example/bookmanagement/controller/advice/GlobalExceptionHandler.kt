package com.example.bookmanagement.controller.advice

import com.example.bookmanagement.config.ApplicationException
import com.example.bookmanagement.model.Response
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.validation.ConstraintViolationException

@ControllerAdvice
class GlobalExceptionHandler {

    private val log = LoggerFactory.getLogger(this::class.java)

    @ExceptionHandler(ConstraintViolationException::class, MethodArgumentNotValidException::class, HttpMessageNotReadableException::class)
    fun handleBadRequestExceptions(ex: Exception): ResponseEntity<Response<*>> {

        log.error("Got exception in handleBadRequestExceptions", ex)

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(Response("ERROR: Bad request input", null))
    }

    @ExceptionHandler(ApplicationException::class)
    fun handleApplicationException(ex: ApplicationException): ResponseEntity<Response<String>> {

        log.error("Got exception in handleApplicationException", ex)

        return ResponseEntity
            .status(ex.httpStatus)
            .body(Response(ex.msg, null))
    }

    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception): ResponseEntity<Response<Nothing>> {

        log.error("Got exception in handleException", ex)

        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(Response())
    }
}