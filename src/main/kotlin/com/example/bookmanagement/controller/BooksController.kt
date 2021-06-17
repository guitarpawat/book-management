package com.example.bookmanagement.controller

import com.example.bookmanagement.config.CommonConstants.MSG_SUCCESS
import com.example.bookmanagement.model.Response
import com.example.bookmanagement.model.request.PostBookRequest
import com.example.bookmanagement.model.response.GetByIdResponse
import com.example.bookmanagement.service.BookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.Min

@RestController
class BooksController(
    @Autowired private val bookService: BookService,
) {

    @GetMapping("/v1/books/{id}")
    fun getById(@PathVariable @Min(1) id: Int): ResponseEntity<Response<GetByIdResponse>> {
        val response = bookService.getById(id)
        return ResponseEntity.ok()
            .body(Response(MSG_SUCCESS, response))
    }

    @PostMapping("/v1/books")
    fun postBook(@RequestBody @Valid request: PostBookRequest): ResponseEntity<Response<Nothing>> {
        bookService.saveBook(request)
        return ResponseEntity.ok()
            .body(Response(MSG_SUCCESS, null))
    }

}