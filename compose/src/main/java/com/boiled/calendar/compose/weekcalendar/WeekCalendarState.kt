package com.boiled.calendar.compose.weekcalendar

import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.boiled.calendar.core.MonthModel
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun rememberWeekCalendarState(
    currentDate: LocalDate = LocalDate.now(),
): WeekCalendarState = rememberSaveable(
    inputs = arrayOf(
        currentDate,
    ),
    saver = WeekCalendarState.Saver
) {
    val monthModel = MonthModel(yearMonth = YearMonth.of(currentDate.year, currentDate.month))

    val weekCalendarState = WeekCalendarState(
        currentDate = currentDate,
        initialPage = run {
            var page = 0
            for (i in monthModel.calendarMonth) {
                if (i.any { it.date == currentDate }) {
                    page = monthModel.calendarMonth.indexOf(i)
                    break
                }
            }
            page
        },
        pageCount = monthModel.calendarMonth.size,
    )
    weekCalendarState
}

class WeekCalendarState(
    initialPage: Int = 0,
    currentDate: LocalDate,
    monthModel: MonthModel = MonthModel(yearMonth = YearMonth.of(currentDate.year, currentDate.month)),
    override val pageCount: Int
) : PagerState(
    currentPage = initialPage
) {
    private var _currentDate: LocalDate by mutableStateOf(currentDate)
    val currentDate: LocalDate get() = _currentDate

    private var _monthModel: MonthModel by mutableStateOf(monthModel)
    val monthModel: MonthModel get() = _monthModel

    private var _currentWeek = derivedStateOf { _monthModel.calendarMonth[currentPage] }
    val currentWeek get() = _currentWeek.value

    companion object {
        val Saver: Saver<WeekCalendarState, *> = listSaver(
            save = {
                listOf(
                    it.currentDate,
                    it.pageCount,
                )
            },
            restore = {
                WeekCalendarState(
                    currentDate = it[0] as LocalDate,
                    pageCount = it[2] as Int,
                )
            }
        )
    }
}