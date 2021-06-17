package com.example.bookmanagement.model.request

import com.example.bookmanagement.model.emuns.BookStatus
import javax.validation.constraints.*

data class PostBookRequest(
    @field:Size(max = 250)
    @field:NotBlank
    val name: String,
    @field:Size(max = 100)
    @field:NotBlank
    val author: String,
    @field:Min(1)
    val edition: Int?,
    @field:NotBlank
    val status: BookStatus,
    val isFavourite: Boolean = false,
)