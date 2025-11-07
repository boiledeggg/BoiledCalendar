package com.boiled.calendar.calendar.compose.monthcalendar

import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import com.boiled.calendar.calendar.compose.monthcalendar.MonthCalendarDefaults.DEFAULT_YEAR_RANGE
import com.boiled.calendar.calendar.model.MonthModel
import com.boiled.calendar.calendar.util.now
import kotlinx.datetime.YearMonth
import kotlinx.datetime.number

@Composable
fun rememberMonthCalendarState(
    currentYearMonth: YearMonth = YearMonth.now(),
    startYear: Int = currentYearMonth.year - DEFAULT_YEAR_RANGE,
    endYear: Int = currentYearMonth.year + DEFAULT_YEAR_RANGE,
): MonthCalendarState {
    val pagerState = rememberPagerState(
        initialPage = calculateCurrentPage(
            currentYear = currentYearMonth.year,
            currentMonth = currentYearMonth.month.number,
            startYear = startYear
        ),
        pageCount = { calculatePageCount(startYear, endYear) },
    )
    return remember {
        MonthCalendarState(
            pagerState = pagerState,
            startYear = startYear,
            endYear = endYear,
        )
    }
}

@Stable
class MonthCalendarState(
    val pagerState: PagerState,
    val startYear: Int,
    val endYear: Int,
) {
    private var _currentYearMonth = derivedStateOf { getYearMonth(pagerState.currentPage) }
    val currentYearMonth get() = _currentYearMonth.value
    private var _currentMonthModel = derivedStateOf { getMonthModel(pagerState.currentPage) }
    val currentMonthModel get() = _currentMonthModel.value

    fun getMonthModel(page: Int): MonthModel {
        val yearMonth = getYearMonth(page = page)
        return getMonthModel(year = yearMonth.year, month = yearMonth.month.number)
    }

    fun getMonthModel(year: Int, month: Int): MonthModel {
        val yearMonth = YearMonth(year, month)
        return MonthModel(yearMonth = yearMonth)
    }

    fun getYearMonth(page: Int): YearMonth = YearMonth(
        startYear + page / 12,
        page % 12 + 1,
    )
}

private fun calculatePageCount(
    startYear: Int,
    endYear: Int,
) = (endYear - startYear + 1) * 12

private fun calculateCurrentPage(
    currentYear: Int,
    currentMonth: Int,
    startYear: Int
): Int = (currentYear - startYear) * 12 + (currentMonth - 1)