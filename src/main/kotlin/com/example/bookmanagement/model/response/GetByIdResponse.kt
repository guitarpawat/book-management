package com.example.bookmanagement.model.response

import com.example.bookmanagement.model.emuns.BookStatus
import java.time.ZonedDateTime

data class GetByIdResponse(
    val id: Int,
    val name: String,
    val author: String,
    val edition: Int?,
    val status: BookStatus = BookStatus.WISH_LIST,
    val isFavourite: Boolean = false,
    val createdDatetime: ZonedDateTime,
)
