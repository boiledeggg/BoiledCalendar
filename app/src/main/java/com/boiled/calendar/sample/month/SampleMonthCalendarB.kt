package com.boiled.calendar.sample.month

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.boiled.calendar.calendar.compose.component.calendarbody.util.CalendarColor
import com.boiled.calendar.calendar.compose.component.header.WeekdaysHeader
import com.boiled.calendar.calendar.compose.monthcalendar.MonthCalendar
import com.boiled.calendar.calendar.compose.monthcalendar.MonthCalendarState
import com.boiled.calendar.calendar.compose.monthcalendar.rememberMonthCalendarState
import com.boiled.calendar.calendar.model.DayModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.Locale

/**
 * ### Sample CalendarB: Simply Customized Month Calendar
 *
 *
 */

@Composable
fun SampleMonthCalendarB(
    modifier: Modifier = Modifier,
) {
    val calendarState = rememberMonthCalendarState()
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }

    MonthCalendar(
        calendarState = calendarState,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 20.dp),
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        dayBody = {
            SampleDayBody(
                dayModel = it,
                isSelected = it.date == selectedDate,
                onClick = { selectedDate = it.date
                Log.d("selectedDate", selectedDate.toString())}
            )
        },
        monthHeader = { SampleMonthHeader() },
        calendarHeader = { SampleCalendarHeader(it) },
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

@Composable
private fun RowScope.SampleDayBody(
    dayModel: DayModel,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    dayBodyColor: CalendarColor = CalendarColor.dayBodyColorDefault,
    onClick: () -> Unit = {},
) {
    val backgroundColor = remember(dayModel, isSelected) {
        if (dayModel.isOutDate) dayBodyColor.outDateContainerColor
        else if (isSelected) dayBodyColor.selectedContainerColor
        else if (dayModel.isToday) dayBodyColor.todayContainerColor
        else dayBodyColor.containerColor
    }

    val textColor = remember(dayModel, isSelected) {
        if (dayModel.isOutDate) dayBodyColor.outDateContentColor
        else if (isSelected) dayBodyColor.selectedContentColor
        else if (dayModel.isToday) dayBodyColor.todayContentColor
        else dayBodyColor.contentColor
    }

    Column(
        modifier = modifier
            .weight(1f)
            .fillMaxHeight()
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier.size(22.dp).background(backgroundColor, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = dayModel.date.dayOfMonth.toString(),
                color = textColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SampleMonthCalendarPreview() {
    SampleMonthCalendarB()
}