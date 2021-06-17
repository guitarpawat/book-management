package com.example.bookmanagement.service

import com.example.bookmanagement.BookRepository
import com.example.bookmanagement.config.ApplicationException
import com.example.bookmanagement.config.BookManagementConfig
import com.example.bookmanagement.config.CommonConstants.HEADER_SECRET
import com.example.bookmanagement.model.mapper.BookEntityMapper
import com.example.bookmanagement.model.request.PostBookRequest
import com.example.bookmanagement.model.response.GetByIdResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataAccessException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class BookService(
    @Autowired private val bookRepository: BookRepository,
    @Autowired private val httpHeaders: HttpHeaders,
    @Autowired private val bookManagementConfig: BookManagementConfig,
) {

    fun getById(id: Int): GetByIdResponse {
        try {
            val bookEntity = bookRepository.getById(id)
            return BookEntityMapper.INSTANCE.mapFromEntityToGetByIdResponse(bookEntity)
        } catch (ex: DataAccessException) {
            throw ApplicationException(HttpStatus.NOT_FOUND, "ERROR: requested id was not found", ex)
        }
    }

    fun saveBook(request: PostBookRequest) {
        if(bookManagementConfig.auth.password != httpHeaders.getFirst(HEADER_SECRET)) {
            throw ApplicationException(HttpStatus.UNAUTHORIZED, "ERROR: Invalid secret")
        }
        val bookEntity = BookEntityMapper.INSTANCE.mapFromPostBookRequestToEntity(request)
        bookRepository.save(bookEntity)
    }
}