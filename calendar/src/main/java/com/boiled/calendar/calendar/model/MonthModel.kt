package com.boiled.calendar.calendar.model

import androidx.compose.runtime.Immutable
import com.boiled.calendar.calendar.util.now
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.YearMonth
import kotlinx.datetime.isoDayNumber
import kotlinx.datetime.minus
import kotlinx.datetime.number
import kotlinx.datetime.plus
import kotlinx.datetime.yearMonth

@Immutable
data class MonthModel(
    val yearMonth: YearMonth
) {
    private val firstDayOfWeek = yearMonth.firstDay.dayOfWeek.isoDayNumber

    val inDays = firstDayOfWeek % 7
    val monthDays = yearMonth.lastDay.day
    val outDays = (7 - ((inDays + monthDays) % 7)) % 7
    val totalDays = monthDays + inDays + outDays

    private val rows = (0 until totalDays).chunked(7)

    val calendarMonth: List<List<DayModel>> = rows.map { week ->
        week.map { dayOffset ->
            getDay(dayOffset)
        }
    }

    private fun getDay(dayOffset: Int): DayModel {
        val firstDayOnCalendar = yearMonth.firstDay.minus(DatePeriod(days = inDays))
        val date = firstDayOnCalendar.plus(DatePeriod(days = dayOffset))
        val isOutDate = date.yearMonth != yearMonth
        val isToday = date == LocalDate.now()
        return DayModel(date, isToday, isOutDate)
    }

    // format: yyyy-MM
    override fun toString(): String {
        val yearString = yearMonth.year.toString()
        val monthString = yearMonth.month.number.toString().padStart(2, '0')

        return DEFAULT_FORMAT.format(yearString, monthString)
    }

    companion object {
        private const val DEFAULT_FORMAT = "%s-%s"
    }
}
