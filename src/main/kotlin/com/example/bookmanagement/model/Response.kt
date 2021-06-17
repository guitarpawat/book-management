package com.example.bookmanagement.model

import com.example.bookmanagement.config.CommonConstants.MSG_GENERIC_ERROR

data class Response<T>(
    val msg: String = MSG_GENERIC_ERROR,
    val data: T? = null,
)