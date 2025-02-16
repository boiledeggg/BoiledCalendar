package com.boiled.calendar.core

import androidx.compose.runtime.Stable
import java.time.LocalDate
import java.time.Month
import java.time.YearMonth

/**
 * Class representing overall calendar model.
 * By following two parameters, this class can determine the size of calendar.
 *
 * @param startYear The start year of the calendar.
 * @param endYear The end year of the calendar.
 */
@Stable
class CalendarModel(
    startYear: Int = YearMonth.now().year - YEAR_RANGE,
    endYear: Int = YearMonth.now().year + YEAR_RANGE,
) {
    private val currentDate = LocalDate.now()
    private val startYearDate = YearMonth.of(startYear, Month.JANUARY.value)
    private val endYearDate = YearMonth.of(endYear, Month.DECEMBER.value)

    val pageCount = (endYearDate.year - startYearDate.year + 1) * MONTH_COUNT
    val initialPage =
        (currentDate.year - startYearDate.year) * MONTH_COUNT + currentDate.monthValue - 1

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
        startYearDate.year + page / MONTH_COUNT,
        page % MONTH_COUNT + 1,
    )

    companion object {
        const val YEAR_RANGE = 5
        const val MONTH_COUNT = 12
    }
}