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
import kotlinx.datetime.YearMonth
import kotlinx.datetime.plusMonth
import org.junit.Rule
import org.junit.Test

class MonthCalendarStateTest {
    private lateinit var calendarState: MonthCalendarState;

    @get:Rule
    val composeTestRule = createComposeRule()
    private val stateRestorationTester = StateRestorationTester(composeTestRule)

    @Test
    fun monthCalendarState_SavableRestored_afterConfiguration() {
        // given
        val yearMonth = YearMonth(2026, 4)
        stateRestorationTester.setContent {
            calendarState = rememberMonthCalendarState(currentYearMonth = yearMonth)
            Column {
                MonthCalendar(
                    calendarState = calendarState,
                    dayBody = null,
                    weekBody = null,
                    monthHeader = null,
                    calendarHeader = {
                        Text(
                            text = this@MonthCalendarStateTest.calendarState.currentYearMonth.toString(),
                        )
                    },
                )
            }
        }

        // when
        composeTestRule.runOnUiThread {
            runBlocking {
                calendarState.pagerState.scrollToPage(calendarState.pagerState.currentPage + 1)
            }
        }
        stateRestorationTester.emulateSavedInstanceStateRestore()
        composeTestRule.waitForIdle()

        // then
        assertEquals(yearMonth.plusMonth(), calendarState.currentYearMonth)
    }
}