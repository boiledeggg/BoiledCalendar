package com.boiled.calendar.calendar.compose.monthcalendar

import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.boiled.calendar.calendar.compose.monthcalendar.MonthCalendarDefaults.defaultEndYear
import com.boiled.calendar.calendar.compose.monthcalendar.MonthCalendarDefaults.defaultStartYear
import com.boiled.calendar.calendar.model.MonthModel
import java.time.Year
import java.time.YearMonth

/**
 * stores and maintains MonthCalendarState
 *
 * It can be used in Pagers as a PagerState, since it implements PagerState.
 * ```
 * @Composable
 * fun MonthCalendar() {
 *      val calendarState = rememberMonthCalendarState()
 *      HorizontalPager(
 *          state = calendarState,
 *      ) { page ->
 *          // contents
 *      }
 * }
 * ```
 *
 * @param startYear the first year of the calendar
 * @param endYear the last year of the calendar
 * @param currentYearMonth the current year and month, which will be initially displayed in the calendar
 *
 * @return [MonthCalendarState]
 */

@Composable
fun rememberMonthCalendarState(
    currentYearMonth: YearMonth = YearMonth.now(),
    startYear: Int = defaultStartYear(currentYearMonth),
    endYear: Int = defaultEndYear(currentYearMonth),
): MonthCalendarState = rememberSaveable(
    inputs = arrayOf(
        startYear,
        endYear,
        currentYearMonth,
    ),
    saver = MonthCalendarState.Saver
) {
    MonthCalendarState(
        startYear = startYear,
        endYear = endYear,
        currentYearMonth = currentYearMonth,
        pageCount = calculatePageCount(startYear, endYear),
    )
}

/**
 * class storing and maintaining information of current month in PagerScope.
 *
 * It implements [PagerState], allowing it to be used in PagerScoped Composable.
 *
 * @property [currentYearMonth] current Year and Month shown
 * @property [currentMonthModel] MonthModel object of current Year/Month
 */

class MonthCalendarState(
    currentYearMonth: YearMonth,
    startYear: Int,
    endYear: Int,
    override val pageCount: Int,
) : PagerState(
    currentPage = calculateCurrentPage(
        currentYear = currentYearMonth.year,
        currentMonth = currentYearMonth.monthValue,
        startYear = startYear
    )
) {
    private var _startYear: Year by mutableStateOf(Year.of(startYear))
    val startYear: Year get() = _startYear

    private var _endYear: Year by mutableStateOf(Year.of(endYear))
    val endYear: Year get() = _endYear

    private var _currentYearMonth = derivedStateOf { getYearMonth(currentPage) }
    val currentYearMonth get() = _currentYearMonth.value

    private var _currentMonthModel = derivedStateOf { getMonthModel(currentPage) }
    val currentMonthModel get() = _currentMonthModel.value

    /**
     * Returns the [YearMonth] for the given page.
     */
    fun getMonthModel(page: Int): MonthModel {
        val yearMonth = getYearMonth(page = page)
        return getMonthModel(year = yearMonth.year, month = yearMonth.monthValue)
    }

    /**
     * Returns the [MonthModel] for the given YearMonth object.
     */
    fun getMonthModel(
        year: Int,
        month: Int,
    ): MonthModel {
        val yearMonth = YearMonth.of(year, month)
        return MonthModel(yearMonth = yearMonth)
    }

    /**
     * Returns the [YearMonth] for the given page.
     */
    fun getYearMonth(page: Int): YearMonth = YearMonth.of(
        startYear.value + page / 12,
        page % 12 + 1,
    )


    companion object {
        val Saver: Saver<MonthCalendarState, *> = listSaver(
            save = {
                listOf(
                    it.startYear,
                    it.endYear,
                    it.currentYearMonth,
                    it.pageCount,
                )
            },
            restore = {
                MonthCalendarState(
                    startYear = (it[0] as Year).value,
                    endYear = (it[1] as Year).value,
                    currentYearMonth = (it[2] as YearMonth),
                    pageCount = it[3] as Int,
                )
            },
        )
    }
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