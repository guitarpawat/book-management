package com.example.bookmanagement.model.request

import com.example.bookmanagement.model.emuns.BookStatus
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class PostBookRequest(
    @field:Size(max = 250)
    @field:NotBlank
    val name: String,
    @field:Size(max = 100)
    @field:NotBlank
    val author: String,
    @field:Min(1)
    val edition: Int?,
    @field:NotNull
    val status: BookStatus,
    val isFavourite: Boolean = false,
)