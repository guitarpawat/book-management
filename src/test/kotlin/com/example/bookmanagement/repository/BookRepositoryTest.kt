package com.example.bookmanagement.repository

import com.example.bookmanagement.model.db.BookEntity
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
internal class BookRepositoryTest(
    @Autowired val testEntityManager: TestEntityManager,
    @Autowired val bookRepository: BookRepository,
) {

    @Test
    fun `findByFavouriteAndStatusCustomQuery should find inserted entity`() {
        val newEntity = BookEntity(
            null,
            "mock name",
            "mock author",
            3,
            "ON_HOLD",
            true,
            null,
        )
        val id = testEntityManager.persistAndGetId(newEntity)

        val matchedEntityList = bookRepository.findByFavouriteAndStatusCustomQuery(true, "ON_HOLD")
        assertTrue(matchedEntityList.any { it.id == id })
    }

    @Test
    fun `updateFavourite should update favourite`() {
        val newEntity = BookEntity(
            null,
            "mock name",
            "mock author",
            3,
            "ON_HOLD",
            false,
            null,
        )
        val id = testEntityManager.persistAndGetId(newEntity) as Int

        bookRepository.updateFavourite(id, true)

        val result = bookRepository.getById(id)
        assertTrue(result.isFavourite)
    }
}