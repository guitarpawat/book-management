package com.example.bookmanagement.controller

import com.example.bookmanagement.config.CommonConstants
import com.example.bookmanagement.config.CommonConstants.OBJECT_MAPPER
import com.example.bookmanagement.model.emuns.BookStatus
import com.example.bookmanagement.model.request.PostBookRequest
import com.example.bookmanagement.model.response.GetByIdResponse
import com.example.bookmanagement.model.response.PostBookResponse
import com.example.bookmanagement.service.BookService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.ZonedDateTime

@WebMvcTest
internal class BooksControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockkBean
    lateinit var bookService: BookService

    @Test
    fun `getById should rejects id that less than 1`() {
        mockMvc.perform(get("/v1/books/0")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isBadRequest)
            .andDo(print())

        verify(exactly = 0) { bookService.getById(any()) }
    }

    @Test
    fun `getById should accepts id that more than 0`() {
        every { bookService.getById(1) } returns
                GetByIdResponse(
                    1,
                    "mock name",
                    "mock author",
                    2,
                    "TO_BE_READ",
                    false,
                    ZonedDateTime.now(CommonConstants.DEFAULT_ZONE_ID),
                )

        mockMvc.perform(get("/v1/books/1")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("msg").value("SUCCESS"))
            .andExpect(jsonPath("data.id").value(1))
            .andDo(print())

        verify(exactly = 1) { bookService.getById(1) }
    }

    @Test
    fun `postBook should success on valid request`() {
        val request = PostBookRequest(
            "mock book",
            "mock author",
            1,
            BookStatus.TO_BE_READ,
            true,
        )

        every { bookService.saveBook(request) } returns PostBookResponse(5)

        mockMvc.perform(post("/v1/books")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(OBJECT_MAPPER.writeValueAsString(request))
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("msg").value("SUCCESS"))
            .andExpect(jsonPath("data.id").value(5))
            .andDo(print())

        verify(exactly = 1) { bookService.saveBook(request) }
    }
}