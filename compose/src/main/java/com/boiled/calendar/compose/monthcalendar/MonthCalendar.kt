package com.boiled.calendar.compose.monthcalendar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.boiled.calendar.compose.component.calendarbody.MonthBody
import com.boiled.calendar.compose.component.header.WeekdaysHeader
import com.boiled.calendar.core.DayModel
import java.util.Locale

@Composable
fun MonthCalendar(
    calendarState: MonthCalendarState,
    modifier: Modifier = Modifier,
    userScrollEnabled: Boolean = true,
    verticalInnerPadding: Dp = 0.dp,
    horizontalInnerPadding: Dp = 0.dp,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    calendarHeader: (@Composable ColumnScope.(MonthCalendarState) -> Unit)?,
    monthHeader: (@Composable ColumnScope.(MonthCalendarState) -> Unit)?,
    weekBody: (@Composable ColumnScope.(List<DayModel>, content: @Composable () -> Unit) -> Unit)?,
    dayBody: (@Composable RowScope.(DayModel) -> Unit)?,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        calendarHeader?.invoke(this, calendarState)
        monthHeader?.invoke(this, calendarState)

        HorizontalPager(
            state = calendarState,
            userScrollEnabled = userScrollEnabled,
            contentPadding = contentPadding
        ) { page ->
            MonthBody(
                monthModel = calendarState.getMonthModel(page),
                weekBody = weekBody,
                dayBody = dayBody,
                verticalInnerPadding = verticalInnerPadding,
                horizontalInnerPadding = horizontalInnerPadding,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MonthCalendarPreview() {
    val calendarState = rememberMonthCalendarState()
    MonthCalendar(
        calendarState = calendarState,
        dayBody = null,
        weekBody = null,
        monthHeader = {
            WeekdaysHeader(
                locale = Locale.KOREA,
                modifier = Modifier.padding(vertical = 20.dp)
            )
            HorizontalDivider()
        },
        calendarHeader = null,
    )
}