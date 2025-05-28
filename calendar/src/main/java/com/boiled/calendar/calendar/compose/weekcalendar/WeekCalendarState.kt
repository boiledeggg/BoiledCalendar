package com.boiled.calendar.calendar.compose.weekcalendar

import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.boiled.calendar.calendar.model.MonthModel
import java.time.LocalDate
import java.time.YearMonth

/**
 * stores and maintains WeekCalendarState
 *
 * It can be used in Pagers as a PagerState, since it implements PagerState.
 * ```
 * @Composable
 * fun WeekCalendar() {
 *      val calendarState = rememberWeekCalendarState()
 *      HorizontalPager(
 *          state = calendarState,
 *      ) { page ->
 *          // contents
 *      }
 * }
 * ```
 * @param currentDate date to initialize week calendar. Month and week to be shown will be decided by this value.
 *
 * @return [WeekCalendarState]
 */

@Composable
fun rememberWeekCalendarState(
    currentDate: LocalDate = LocalDate.now(),
): WeekCalendarState = rememberSaveable(
    inputs = arrayOf(currentDate),
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

@Stable
class WeekCalendarState(
    initialPage: Int = 0,
    currentDate: LocalDate,
    override val pageCount: Int
) : PagerState(
    currentPage = initialPage
) {
    private var _currentDate: LocalDate by mutableStateOf(currentDate)
    val currentDate: LocalDate get() = _currentDate

    private var _monthModel: MonthModel by mutableStateOf(
        MonthModel(
            yearMonth = YearMonth.of(
                currentDate.year,
                currentDate.month
            )
        ),
    )
    val monthModel: MonthModel get() = _monthModel

    private var _currentWeek = derivedStateOf { _monthModel.calendarMonth[currentPage] }
    val currentWeek get() = _currentWeek.value

    private var _currentWeekPage = derivedStateOf { currentPage }
    val currentWeekPage get() = _currentWeekPage.value

    companion object {
        val Saver: Saver<WeekCalendarState, *> = listSaver(
            save = {
                listOf(
                    it.currentDate,
                    it.pageCount,
                    it.currentWeekPage
                )
            },
            restore = {
                WeekCalendarState(
                    currentDate = it[0] as LocalDate,
                    pageCount = it[1] as Int,
                    initialPage = it[2] as Int,
                )
            }
        )
    }
}