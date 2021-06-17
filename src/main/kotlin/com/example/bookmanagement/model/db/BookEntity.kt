package com.example.bookmanagement.model.db

import java.sql.Timestamp
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    var createdDatetime: Timestamp?,
)