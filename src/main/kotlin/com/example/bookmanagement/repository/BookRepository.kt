package com.example.bookmanagement.repository

import com.example.bookmanagement.model.db.BookEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
@Transactional
interface BookRepository: JpaRepository<BookEntity, Int> {
    // For JPA query, we use entity class name and variable name
    @Query("select b from BookEntity b where b.isFavourite = :isFavourite and b.status = :status")
    fun findByFavouriteAndStatusCustomQuery(
        isFavourite: Boolean,
        status: String,
    ): List<BookEntity>

    // For native query, we use name from database same as JDBC query
    @Query(
        value = "select * from Book b where b.is_favourite = :isFavourite and b.status = :status",
        nativeQuery = true
    )
    fun findByFavouriteAndStatusNativeQuery(
        isFavourite: Boolean,
        status: String,
    ): List<BookEntity>



    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("update BookEntity b set b.isFavourite = :isFavourite where b.id = :id")
    fun updateFavourite(
        id: Int,
        isFavourite: Boolean,
    )
}