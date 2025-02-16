package com.boiled.calendar.core

import org.junit.Before
import org.junit.Test
import java.time.YearMonth
import kotlin.test.assertEquals

class CalendarModelUnitTest {
    lateinit var calendarModel: CalendarModel

    @Before
    fun setUp() {
        val currentYear = YearMonth.now()
        calendarModel = CalendarModel(
            startYear = currentYear.year,
            endYear = currentYear.year,
        )
    }

    @Test
    fun `check if page settings are done correctly` () {
        val expectedPageNum = YearMonth.now().monthValue - 1

        assertEquals(calendarModel.initialPage, expectedPageNum)
        assertEquals(calendarModel.pageCount, 12)
    }

    @Test
    fun `check if getMonthModel returns correct MonthModel`() {
        val pageNum = YearMonth.now().monthValue - 1

        val expectedMonthModel = MonthModel(yearMonth = YearMonth.now())
        val actualMonthModel = calendarModel.getMonthModel(page = pageNum)

        assertEquals(expectedMonthModel.yearMonth, actualMonthModel.yearMonth)
        assertEquals(expectedMonthModel.inDays, actualMonthModel.inDays)
        assertEquals(expectedMonthModel.monthDays, actualMonthModel.monthDays)
        assertEquals(expectedMonthModel.outDays, actualMonthModel.outDays)
        assertEquals(expectedMonthModel.totalDays, actualMonthModel.totalDays)
    }
}