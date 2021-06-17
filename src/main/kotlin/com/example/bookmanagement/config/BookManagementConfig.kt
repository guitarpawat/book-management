package com.example.bookmanagement.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "book-management-config")
data class BookManagementConfig(
    val auth: Auth,
    val mockString: String,
)

data class Auth(
    val password: String,
)