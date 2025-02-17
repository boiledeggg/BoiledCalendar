package com.boiled.calendar.core

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.time.LocalDate

class DayModelUnitTest {

    @Test
    fun `check whether the day is today`() {
        val today = LocalDate.now()
        val dayModel = DayModel(date = today, isOutDate = false)

        assert(dayModel.isToday)
    }

    @Test
    fun `check if scrapMapKey is in correct format`() {
        val today = LocalDate.of(2025, 1, 1)
        val dayModel = DayModel(date = today, isOutDate = false)

        assertEquals("2025-01-01", dayModel.scrapMapKey)
    }
}
