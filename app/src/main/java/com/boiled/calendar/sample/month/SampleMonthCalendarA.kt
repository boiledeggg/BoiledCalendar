package com.boiled.calendar.sample.month

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.boiled.calendar.calendar.compose.component.header.WeekdaysHeader
import com.boiled.calendar.calendar.compose.monthcalendar.MonthCalendar
import com.boiled.calendar.calendar.compose.monthcalendar.MonthCalendarState
import com.boiled.calendar.calendar.compose.monthcalendar.rememberMonthCalendarState
import kotlinx.coroutines.launch
import java.util.Locale


/**
 * ### Sample Calendar A: Default Month Calendar View
 *
 * This calendar is default month calendar of this library, created when no parameter is passed
 * for any composable component. No design is assign to this calendar.
 *
 * Simple headers are added to mock general calendars.
 * Check out [SampleCalendarHeader], you can find out how to manipulate pagerState of this calendar.
 */

@Composable
fun SampleMonthCalendarA(
    modifier: Modifier = Modifier,
) {
    val calendarState = rememberMonthCalendarState()

    MonthCalendar(
        calendarState = calendarState,
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    )
}

@Composable
private fun SampleCalendarHeader(
    calendarState: MonthCalendarState,
    modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            contentDescription = null,
            modifier = Modifier.clickable {
                coroutineScope.launch {
                    calendarState.animateScrollToPage(calendarState.currentPage - 1)
                }
            }
        )
        Text(
            text = calendarState.currentYearMonth.toString()
        )
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            modifier = Modifier.clickable {
                coroutineScope.launch {
                    calendarState.animateScrollToPage(calendarState.currentPage + 1)
                }
            }
        )
    }
}

@Composable
private fun SampleMonthHeader(
    modifier: Modifier = Modifier,
) {
    WeekdaysHeader(
        locale = Locale.US,
        contentPadding = PaddingValues(16.dp),
    )
    HorizontalDivider()
}



@Preview(showBackground = true)
@Composable
private fun SampleMonthCalendarPreview() {
    SampleMonthCalendarA()
}