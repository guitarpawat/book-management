package com.example.bookmanagement.repository

import com.example.bookmanagement.model.db.BookEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
@Transactional
interface BookRepository: JpaRepository<BookEntity, Int> {
    @Query("select b from BookEntity b where b.isFavourite = :isFavourite and b.status = :status")
    fun findByFavouriteAndStatusCustomQuery(
        @Param("isFavourite") isFavourite: Boolean,
        @Param("status") status: String,
    ): List<BookEntity>

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("update BookEntity b set b.isFavourite = :isFavourite where b.id = :id")
    fun updateFavourite(
        @Param("id") id: Int,
        @Param("isFavourite") isFavourite: Boolean,
    )
}