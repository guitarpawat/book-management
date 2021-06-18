package com.example.bookmanagement.model.db

import org.hibernate.annotations.CreationTimestamp
import java.time.ZonedDateTime
import javax.persistence.*

@Entity
@Table(name = "Book")
data class BookEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,
    var name: String,
    var author: String,
    var edition: Int?,
    var status: String,
    var isFavourite: Boolean,
    @CreationTimestamp
    var createdDatetime: ZonedDateTime?,
)