package com.boiled.calendar.calendar.util

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.YearMonth
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.yearMonth
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
fun LocalDateTime.Companion.now(): LocalDateTime {
    val currentTime: Instant = Clock.System.now()
    val now: LocalDateTime = currentTime.toLocalDateTime(TimeZone.currentSystemDefault())
    return now
}

fun LocalDate.Companion.now(): LocalDate {
    val now = LocalDateTime.now()
    return now.date
}

fun LocalDate.getFormattedString(
    format: DateTimeFormat<LocalDate> = DateTimeDefault.format,
): String {
    return format(format)
}

fun YearMonth.Companion.now(): YearMonth {
    val today = LocalDate.now()
    val currentYearMonth = today.yearMonth
    return currentYearMonth
}
