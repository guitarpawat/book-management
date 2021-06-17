package com.example.bookmanagement.model.emuns

enum class BookStatus {
    WISH_LIST {
        override fun nextStatus() = listOf(BOUGHT)
    },
    BOUGHT {
        override fun nextStatus() = listOf(TO_BE_READ)
    },
    TO_BE_READ {
        override fun nextStatus() = listOf(BOUGHT, READING)
    },
    READING {
        override fun nextStatus() = listOf(ON_HOLD, DONE_READING)
    },
    ON_HOLD {
        override fun nextStatus() = listOf(BOUGHT, TO_BE_READ, READING)
    },
    DONE_READING {
        override fun nextStatus() = listOf(TO_BE_READ, READING)
    },
    ;

    abstract fun nextStatus(): List<BookStatus>
}