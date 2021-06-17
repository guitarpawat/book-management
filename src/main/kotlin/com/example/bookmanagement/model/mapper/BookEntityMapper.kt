package com.example.bookmanagement.model.mapper

import com.example.bookmanagement.model.db.BookEntity
import com.example.bookmanagement.model.request.PostBookRequest
import com.example.bookmanagement.model.response.GetByIdResponse
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper
interface BookEntityMapper {
    companion object {
        val INSTANCE = Mappers.getMapper(BookEntityMapper::class.java)
    }

    fun mapFromEntityToGetByIdResponse(entity: BookEntity): GetByIdResponse

    fun mapFromPostBookRequestToEntity(request: PostBookRequest): BookEntity
}