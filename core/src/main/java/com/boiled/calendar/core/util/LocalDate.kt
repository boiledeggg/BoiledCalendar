package com.boiled.calendar.core.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * returns pattern string of [LocalDate] given as parameter
 */
fun LocalDate.getFormattedString(pattern: String): String  {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return format(formatter)
}
