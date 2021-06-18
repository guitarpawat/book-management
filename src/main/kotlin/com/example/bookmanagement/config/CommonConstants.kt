package com.example.bookmanagement.config

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import java.time.ZoneId

object CommonConstants {
    const val MSG_SUCCESS = "SUCCESS"
    const val MSG_GENERIC_ERROR = "ERROR: Generic error"

    const val HEADER_SECRET = "secret"

    const val DEFAULT_TIME_ZONE = "Asia/Bangkok"
    val DEFAULT_ZONE_ID = ZoneId.of(DEFAULT_TIME_ZONE)

    val OBJECT_MAPPER = ObjectMapper()
        .registerModule(kotlinModule())
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
}
