package com.example.bookmanagement.config

import com.example.bookmanagement.config.CommonConstants.MSG_GENERIC_ERROR
import org.springframework.http.HttpStatus

class ApplicationException(
    val httpStatus: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
    val msg: String = MSG_GENERIC_ERROR,
    val ex: Exception? = null,
) : Exception(msg, ex) {
}