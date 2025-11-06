package com.boiled.calendar.calendar.compose.weekcalendar

import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.boiled.calendar.calendar.model.MonthModel
import com.boiled.calendar.calendar.util.now
import kotlinx.datetime.LocalDate
import kotlinx.datetime.YearMonth

@Composable
fun rememberWeekCalendarState(
    currentDate: LocalDate = LocalDate.now(),
): WeekCalendarState {
    val monthModel = MonthModel(yearMonth = YearMonth(currentDate.year, currentDate.month))
    val initialPage = run {
        var page = 0
        monthModel.calendarMonth.forEachIndexed { index, week ->
            if (week.any { dayModel -> dayModel.date == currentDate }) {
                page = index
                return@forEachIndexed
            }
        }
        page
    }

    val pagerState = rememberPagerState(
        initialPage = initialPage,
        pageCount = { monthModel.calendarMonth.size }
    )


    return WeekCalendarState(
        pagerState = pagerState,
        currentDate = currentDate,
    )
}

@Stable
class WeekCalendarState(
    val pagerState: PagerState,
    currentDate: LocalDate,
) {
    private var _currentDate: LocalDate by mutableStateOf(currentDate)
    val currentDate: LocalDate get() = _currentDate

    private var _monthModel: MonthModel by mutableStateOf(
        MonthModel(
            yearMonth = YearMonth(
                currentDate.year,
                currentDate.month
            )
        ),
    )
    val monthModel: MonthModel get() = _monthModel

    private var _currentWeek = derivedStateOf { _monthModel.calendarMonth[pagerState.currentPage] }
    val currentWeek get() = _currentWeek.value

    private var _currentWeekPage = derivedStateOf { pagerState.currentPage }
    val currentWeekPage get() = _currentWeekPage.value
}