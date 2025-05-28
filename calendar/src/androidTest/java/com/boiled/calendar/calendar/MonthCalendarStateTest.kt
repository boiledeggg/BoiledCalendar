package com.boiled.calendar.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.createComposeRule
import com.boiled.calendar.calendar.compose.monthcalendar.MonthCalendar
import com.boiled.calendar.calendar.compose.monthcalendar.MonthCalendarState
import com.boiled.calendar.calendar.compose.monthcalendar.rememberMonthCalendarState
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import java.time.YearMonth

class MonthCalendarStateTest {
    private lateinit var state: MonthCalendarState;

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun monthCalendarState_SavableRestored_afterConfiguration() {
        val stateRestorationTester = StateRestorationTester(composeTestRule)
        val yearMonth = YearMonth.of(2026, 4)

        stateRestorationTester.setContent {
            state = rememberMonthCalendarState(currentYearMonth = yearMonth)

            Column {
                MonthCalendar(
                    calendarState = state,
                    dayBody = null,
                    weekBody = null,
                    monthHeader = null,
                    calendarHeader = {
                        Text(
                            text = state.currentYearMonth.toString(),
                        )
                    },
                )
            }
        }

        composeTestRule.runOnUiThread {
            runBlocking {
                state.scrollToPage(state.currentPage + 1)
            }
        }

        stateRestorationTester.emulateSavedInstanceStateRestore()
        composeTestRule.waitForIdle()

        assertEquals(yearMonth.plusMonths(1), state.currentYearMonth)
    }
}