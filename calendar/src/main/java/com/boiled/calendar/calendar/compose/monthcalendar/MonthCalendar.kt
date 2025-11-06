package com.boiled.calendar.calendar.compose.monthcalendar

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
import com.boiled.calendar.calendar.compose.component.calendarbody.MonthBody
import com.boiled.calendar.calendar.compose.component.header.WeekdaysHeader
import com.boiled.calendar.calendar.model.DayModel

/**
 * ## Customizable and scrollable month calendar view
 *
 * This composable allows user to customize month calendar view.
 * Main customizing concept of this calendar's customization is passing **customized day cell**.
 *
 * To customize day cell, user can pass COMPOSABLE function to `dayBody` parameter.
 * By passing user customized cell, one can experience uniformly designed calendar.
 *
 * Rest of the parameters' explanations are given below
 *
 * @param calendarState [MonthCalendarState] object which contains information of current month.
 * @param userScrollEnabled Whether user can scroll the calendar.
 * @param verticalInnerPadding Padding between weeks.
 * @param horizontalInnerPadding Padding between days.
 * @param contentPadding Padding at the edge of the calendar.
 * @param calendarHeader Composable which represents header of the calendar.
 * @param monthHeader Composable which represents header of the month, mainly used to show weekdays name
 * @param weekBody composable receiving a week content as parameter.
 * @param dayBody composable which represents a cell in the calendar table.
 */

@Composable
fun MonthCalendar(
    calendarState: MonthCalendarState,
    modifier: Modifier = Modifier,
    userScrollEnabled: Boolean = true,
    verticalInnerPadding: Dp = 0.dp,
    horizontalInnerPadding: Dp = 0.dp,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    calendarHeader: (@Composable ColumnScope.(MonthCalendarState) -> Unit)? = null,
    monthHeader: (@Composable ColumnScope.(MonthCalendarState) -> Unit)? = { WeekdaysHeader() },
    weekBody: (@Composable ColumnScope.(List<DayModel>, content: @Composable () -> Unit) -> Unit)? = null,
    dayBody: (@Composable RowScope.(DayModel) -> Unit)? = null,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        calendarHeader?.invoke(this, calendarState)
        monthHeader?.invoke(this, calendarState)

        HorizontalPager(
            state = calendarState.pagerState,
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
                modifier = Modifier.padding(vertical = 20.dp)
            )
            HorizontalDivider()
        },
        calendarHeader = null,
    )
}