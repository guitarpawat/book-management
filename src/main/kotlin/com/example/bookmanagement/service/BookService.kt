package com.example.bookmanagement.service

import com.example.bookmanagement.config.ApplicationException
import com.example.bookmanagement.config.BookManagementConfig
import com.example.bookmanagement.config.CommonConstants.HEADER_SECRET
import com.example.bookmanagement.model.db.BookEntity
import com.example.bookmanagement.model.request.PostBookRequest
import com.example.bookmanagement.model.response.GetByIdResponse
import com.example.bookmanagement.model.response.PostBookResponse
import com.example.bookmanagement.repository.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException
import org.springframework.stereotype.Service

@Service
class BookService(
    @Autowired private val bookRepository: BookRepository,
    @Autowired private val httpHeaders: HttpHeaders,
    @Autowired private val bookManagementConfig: BookManagementConfig,
) {

    fun getById(id: Int): GetByIdResponse {
        try {
            val bookEntity = bookRepository.getById(id)
            return GetByIdResponse(
                bookEntity.id!!,
                bookEntity.name,
                bookEntity.author,
                bookEntity.edition,
                bookEntity.status,
                bookEntity.isFavourite,
                bookEntity.createdDatetime!!,
            )
        } catch (ex: JpaObjectRetrievalFailureException) {
            throw ApplicationException(HttpStatus.NOT_FOUND, "ERROR: requested id was not found", ex)
        }
    }

    fun saveBook(request: PostBookRequest): PostBookResponse {
        if(bookManagementConfig.auth.password != httpHeaders.getFirst(HEADER_SECRET)) {
            throw ApplicationException(HttpStatus.UNAUTHORIZED, "ERROR: Invalid secret")
        }
        val bookEntity = BookEntity(
            null,
            request.name,
            request.author,
            request.edition,
            request.status.name,
            request.isFavourite,
            null,
        )
        val result = bookRepository.save(bookEntity)
        return PostBookResponse(result.id!!)
    }
}