package com.example.bookmanagement

import com.example.bookmanagement.model.db.BookEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
@Transactional
interface BookRepository: JpaRepository<BookEntity, Int> {
}

