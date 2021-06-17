package com.example.bookmanagement.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.http.HttpHeaders
import java.util.*
import javax.servlet.http.HttpServletRequest

@Configuration
class HttpHeadersConfig(
    @Autowired val request: HttpServletRequest,
    ) {

    @Bean
    @Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
    fun getHttpHeaders(): HttpHeaders {
        val httpHeaders = HttpHeaders()
        val headerFields = request.headerNames
        for(headerField in headerFields) {
            httpHeaders[headerField] = Collections.list(request.getHeaders(headerField))
        }

        return httpHeaders
    }
}