package com.boiled.calendar.calendar.model

import junit.framework.TestCase.assertEquals
import kotlinx.datetime.LocalDate
import org.junit.Test

class DayModelUnitTest {

    @Test
    fun `check if scrapMapKey is in correct format`() {
        val today = LocalDate(2025, 1, 1)
        val dayModel = DayModel(date = today, isToday = false, isOutDate = false)

        assertEquals("2025-01-01", dayModel.scrapMapKey)
    }
}
