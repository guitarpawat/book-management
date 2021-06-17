package com.example.bookmanagement

import com.example.bookmanagement.config.BookManagementConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(BookManagementConfig::class)
class BookManagementApplication

fun main(args: Array<String>) {
	runApplication<BookManagementApplication>(*args)
}
