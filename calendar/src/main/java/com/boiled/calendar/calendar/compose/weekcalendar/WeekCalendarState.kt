package com.boiled.calendar.calendar.compose.weekcalendar

import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.boiled.calendar.calendar.model.MonthModel
import com.boiled.calendar.calendar.util.now
import kotlinx.datetime.LocalDate
import kotlinx.datetime.YearMonth

@Composable
fun rememberWeekCalendarState(
    currentDate: LocalDate = LocalDate.now(),
): WeekCalendarState {
    val monthModel = remember(currentDate) {
        MonthModel(yearMonth = YearMonth(currentDate.year, currentDate.month))
    }
    val initialPage = remember(monthModel, currentDate) {
        monthModel.calendarMonth.indexOfFirst { week ->
            week.any { dayModel -> dayModel.date == currentDate }
        }
    }

    val pagerState = rememberPagerState(
        initialPage = initialPage,
        pageCount = { monthModel.calendarMonth.size }
    )

    return remember(monthModel, initialPage, currentDate) {
        WeekCalendarState(
            pagerState = pagerState,
            monthModel = monthModel,
            currentDate = currentDate,
        )
    }
}

@Stable
class WeekCalendarState(
    val pagerState: PagerState,
    val monthModel: MonthModel,
    currentDate: LocalDate,
) {
    private var _currentDate: LocalDate by mutableStateOf(currentDate)
    val currentDate: LocalDate get() = _currentDate


    private val _currentWeek = derivedStateOf { monthModel.calendarMonth[pagerState.currentPage] }
    val currentWeek get() = _currentWeek.value

    private val _currentWeekPage = derivedStateOf { pagerState.currentPage }
    val currentWeekPage get() = _currentWeekPage.value
}