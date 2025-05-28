package com.boiled.calendar.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.createComposeRule
import com.boiled.calendar.calendar.compose.weekcalendar.WeekCalendar
import com.boiled.calendar.calendar.compose.weekcalendar.WeekCalendarState
import com.boiled.calendar.calendar.compose.weekcalendar.rememberWeekCalendarState
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

class WeekCalendarStateTest {
    private lateinit var state: WeekCalendarState;

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun weekCalendarState_SavableRestored_afterConfiguration() {
        val stateRestorationTester = StateRestorationTester(composeTestRule)
        val expectedPage = 2

        stateRestorationTester.setContent {
            state = rememberWeekCalendarState()

            Column {
                WeekCalendar(
                    calendarState = state,
                    dayBody = null,
                    weekBody = null,
                    weekHeader = null,
                    calendarHeader = {
                        Text(
                            text = state.currentDate.toString(),
                        )
                    },
                )
            }
        }

        composeTestRule.runOnUiThread {
            runBlocking {
                state.scrollToPage(expectedPage)
            }
        }

        stateRestorationTester.emulateSavedInstanceStateRestore()
        composeTestRule.waitForIdle()

        assertEquals(expectedPage, state.currentPage)
    }
}