package com.boiled.calendar.calendar.compose.monthcalendar

import java.time.YearMonth

object MonthCalendarDefaults {
    private const val DEFAULT_YEAR_RANGE = 5

    fun defaultStartYear(yearMonth: YearMonth) = yearMonth.year - DEFAULT_YEAR_RANGE
    fun defaultEndYear(yearMonth: YearMonth) = yearMonth.year + DEFAULT_YEAR_RANGE
}