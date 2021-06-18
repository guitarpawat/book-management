package com.example.bookmanagement.service

import com.example.bookmanagement.config.ApplicationException
import com.example.bookmanagement.config.BookManagementConfig
import com.example.bookmanagement.config.CommonConstants.DEFAULT_ZONE_ID
import com.example.bookmanagement.model.db.BookEntity
import com.example.bookmanagement.model.emuns.BookStatus
import com.example.bookmanagement.model.request.PostBookRequest
import com.example.bookmanagement.repository.BookRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.HttpHeaders
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException
import java.time.ZonedDateTime
import javax.persistence.EntityNotFoundException

internal class BookServiceTest {
    @MockK
    lateinit var bookRepository: BookRepository

    @MockK
    lateinit var httpHeaders: HttpHeaders

    @MockK
    lateinit var bookManagementConfig: BookManagementConfig

    @InjectMockKs
    lateinit var subject: BookService

    @BeforeEach
    fun setUp() = MockKAnnotations.init(this)

    @Test
    fun `getById should throws HTTP 404 when book not found`() {
        every { bookRepository.getById(any()) } throws JpaObjectRetrievalFailureException(EntityNotFoundException("mock exception"))

        val ex = assertThrows(ApplicationException::class.java) { subject.getById(1) }

        assertEquals(404, ex.httpStatus.value())

        verify(exactly = 1) { bookRepository.getById(any()) }
    }

    @Test
    fun `getById should returns book information when book was found`() {
        val now = ZonedDateTime.now(DEFAULT_ZONE_ID)
        every { bookRepository.getById(1) } returns BookEntity(
            1,
            "mock name",
            "mock author",
            2,
            "TO_BE_READ",
            true,
            now,
        )

        val response = subject.getById(1)

        assertEquals(1, response.id)
        assertEquals("mock name", response.name)
        assertEquals("mock author", response.author)
        assertEquals(2, response.edition)
        assertEquals("TO_BE_READ", response.status)
        assertTrue(response.isFavourite)
        assertEquals(now, response.createdDatetime)

        verify(exactly = 1) { bookRepository.getById(1) }
    }

    @Test
    fun `saveBook should throws HTTP 401 when got invalid secret`() {
        every { httpHeaders.getFirst("secret") } returns "invalid"
        every { bookManagementConfig.auth.password } returns "mock password"

        val ex = assertThrows(ApplicationException::class.java) { subject.saveBook(
            PostBookRequest(
                "mock name",
                "mock author",
                0,
                BookStatus.TO_BE_READ,
                false,
        )) }

        assertEquals(401, ex.httpStatus.value())

        verify(exactly = 0) { bookRepository.save(any()) }
    }

    @Test
    fun `saveBook should returns book id when got valid secret`() {
        every { httpHeaders.getFirst("secret") } returns "mock password"
        every { bookManagementConfig.auth.password } returns "mock password"

        every { bookRepository.save(any()) } returns BookEntity(
            5,
            "mock name",
            "mock author",
            1,
            "TO_BE_READ",
            false,
            ZonedDateTime.now(DEFAULT_ZONE_ID)
        )

        val response = subject.saveBook(
            PostBookRequest(
                "mock name",
                "mock author",
                1,
                BookStatus.TO_BE_READ,
                false,
        ))

        assertEquals(5, response.id)

        verify(exactly = 1) { bookRepository.save(any()) }
    }
}