package com.boiled.calendar.calendar.model

import kotlinx.datetime.LocalDate
import kotlinx.datetime.YearMonth
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MonthModelUnitTest {
    private lateinit var monthModel: MonthModel

    @Before
    fun setUp() {
        monthModel = MonthModel(yearMonth = YearMonth(2025, 2))
    }

    @Test
    fun `check if month page setting is correct`() {
        assertEquals(6, monthModel.inDays)
        assertEquals(28, monthModel.monthDays)
        assertEquals(1, monthModel.outDays)
        assertEquals(35, monthModel.totalDays)
    }

    @Test
    fun `check if correct DayModel is returned`() {
        val dayModel = monthModel.calendarMonth[0][0]

        assertEquals(LocalDate(2025, 1, 26), dayModel.date)
        assertEquals(true, dayModel.isOutDate)
    }

    @Test
    fun `check if StringFormat is correct`() {
        val formattedString = monthModel.toString()
        assertEquals("2025-02", formattedString)
    }
}