package com.boiled.calendar.core

import androidx.compose.runtime.Immutable
import java.time.YearMonth

@Immutable
data class MonthModel(
    val yearMonth: YearMonth
) {
    private val firstDayOfWeek = yearMonth.atDay(1).dayOfWeek.value

    val inDays = firstDayOfWeek % 7
    val monthDays = yearMonth.lengthOfMonth()
    val outDays = (7 - ((inDays + monthDays) % 7)) % 7
    val totalDays = monthDays + inDays + outDays

    private val rows = (0 until totalDays).chunked(7)

    val calendarMonth: List<List<DayModel>> =
        rows.map { week -> week.map { dayOffset -> getDay(dayOffset) } }

    private fun getDay(dayOffset: Int): DayModel {
        val firstDayOnCalendar = yearMonth.atDay(1).minusDays(inDays.toLong())
        val date = firstDayOnCalendar.plusDays(dayOffset.toLong())
        //val weekIndex = (dayOffset / 7)
        val isOutDate = YearMonth.of(date.year, date.monthValue) != yearMonth

        return DayModel(date, isOutDate)
    }

    override fun toString(): String {
        val yearString = yearMonth.year.toString()
        val monthString = yearMonth.monthValue.toString().padStart(2, '0')

        return STRING_FORMAT.format(yearString, monthString)
    }

    companion object {
        private const val STRING_FORMAT = "%s년 %s월"
    }
}