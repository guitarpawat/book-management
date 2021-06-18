package com.example.bookmanagement.model.response

import java.time.ZonedDateTime

data class GetByIdResponse(
    val id: Int,
    val name: String,
    val author: String,
    val edition: Int?,
    val status: String,
    val isFavourite: Boolean = false,
    val createdDatetime: ZonedDateTime,
)
