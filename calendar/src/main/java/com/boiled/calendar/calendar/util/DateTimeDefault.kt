package com.boiled.calendar.calendar.util

import kotlinx.datetime.LocalDate
import kotlinx.datetime.format.char

object DateTimeDefault {

    // Default pattern: yyyy-MM-dd
    val format = LocalDate.Format {
        year()
        char('-')
        monthNumber()
        char('-')
        day()
    }
}