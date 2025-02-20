package com.boiled.calendar.calendar.weekcalendar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerScope
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.boiled.calendar.calendar.component.calendarbody.DefaultDayBody
import com.boiled.calendar.calendar.component.calendarbody.WeekBody
import com.boiled.calendar.calendar.component.header.WeekdaysHeader
import com.boiled.calendar.core.DayModel
import com.boiled.calendar.core.util.ifNull
import kotlinx.collections.immutable.toImmutableList
import java.util.Locale

/**
 * ## Customizable and scrollable week calendar view
 *
 * This composable allows user to customize week calendar view.
 * Main customizing concept of this calendar's customization is passing **customized day cell**.
 *
 * To customize day cell, user can pass COMPOSABLE function to `dayBody` parameter.
 * By passing user customized cell, one can experience uniformly designed calendar.
 *
 * Rest of the parameters' explanations are given below
 *
 * @param calendarState [WeekCalendarState] object which contains information of current month and week.
 * @param userScrollEnabled Whether user can scroll the calendar.
 * @param horizontalInnerPadding Padding between days.
 * @param contentPadding Padding at the edge of the calendar.
 * @param calendarHeader Composable which represents header of the calendar.
 * @param weekHeader Composable which represents header of the month, mainly used to show weekdays name
 * @param weekBody composable receiving a week content as parameter.
 * @param dayBody composable which represents a cell in the calendar table.
 */

@Composable
fun WeekCalendar(
    calendarState: WeekCalendarState,
    modifier: Modifier = Modifier,
    userScrollEnabled: Boolean = true,
    horizontalInnerPadding: Dp = 0.dp,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    calendarHeader: (@Composable ColumnScope.(WeekCalendarState) -> Unit)?,
    weekHeader: (@Composable ColumnScope.(WeekCalendarState) -> Unit)?,
    weekBody: (@Composable PagerScope.(List<DayModel>, content: @Composable () -> Unit) -> Unit)?,
    dayBody: (@Composable RowScope.(DayModel) -> Unit)?,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        calendarHeader?.invoke(this, calendarState)
        weekHeader?.invoke(this, calendarState)

        HorizontalPager(
            state = calendarState,
            modifier = modifier,
            contentPadding = contentPadding,
            userScrollEnabled = userScrollEnabled
        ) { page ->
            val week = calendarState.monthModel.calendarMonth[page]

            weekBody.ifNull(defaultWeekBody)(week) {
                WeekBody(
                    week = week.toImmutableList(),
                    horizontalInnerPadding = horizontalInnerPadding,
                    dayBody = dayBody,
                )
            }
        }
    }
}

// Default implementation of Composable Bodies if not provided
private val defaultWeekBody: (@Composable PagerScope.(List<DayModel>, content: @Composable () -> Unit) -> Unit) =
    { _, content -> content() }

@Preview(showBackground = true)
@Composable
private fun WeekCalendarPreview() {
    val calendarState = rememberWeekCalendarState()
    WeekCalendar(
        calendarState = calendarState,
        contentPadding = PaddingValues(16.dp),
        dayBody = {
            DefaultDayBody(
                dayModel = it,
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            )
        },
        weekBody = null,
        weekHeader = {
            WeekdaysHeader(
                locale = Locale.KOREA,
                contentPadding = PaddingValues(16.dp),
            )
            HorizontalDivider()
        },
        calendarHeader = null,
    )
}