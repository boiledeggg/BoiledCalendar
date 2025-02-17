package com.boiled.calendar.compose.month

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.boiled.calendar.compose.component.calendarbody.DefaultDayBody
import com.boiled.calendar.core.DayModel
import com.boiled.calendar.core.MonthModel
import com.boiled.calendar.core.util.ifNull

/**
 * ## Customizable month calendar view
 *
 * This composable allows user to customize month calendar view.
 * Main customizing concept of this calendar is changing the design of Day Cells.
 *
 * To customize day cell, user can pass COMPOSABLE function to `dayBody` parameter.
 * By passing user customized cell, one can experience uniformly designed calendar.
 *
 * Rest of the parameters' explanations are given below
 *
 * @param monthModel [MonthModel] object which contains information of current month.
 * @param verticalInnerPadding Padding between weeks.
 * @param horizontalInnerPadding Padding between days.
 * @param monthBody composable receiving whole month content as parameter.
 * @param weekBody composable receiving a week content as parameter.
 * @param dayBody composable which represents a cell in the calendar table.
 */

@Composable
fun MonthCalendar(
    monthModel: MonthModel,
    modifier: Modifier = Modifier,
    verticalInnerPadding: Dp = 0.dp,
    horizontalInnerPadding: Dp = 0.dp,
    monthBody: (@Composable (MonthModel, content: @Composable () -> Unit) -> Unit)?,
    weekBody: (@Composable ColumnScope.(List<DayModel>, content: @Composable () -> Unit) -> Unit)?,
    dayBody: (@Composable RowScope.(DayModel) -> Unit)?,
) {
    monthBody.ifNull(defaultMonthBody)(monthModel) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(verticalInnerPadding)
        ) {
            monthModel.calendarMonth.forEach { week ->
                weekBody.ifNull(defaultWeekBody)(week) {
                    Row(
                        modifier = Modifier.weight(1f),
                        horizontalArrangement = Arrangement.spacedBy(horizontalInnerPadding)
                    ) {
                        week.forEach { day ->
                            dayBody?.invoke(this, day)
                                ?: DefaultDayBody(
                                    dayModel = day,
                                    modifier = Modifier.weight(1f)
                                )
                        }
                    }
                }
            }
        }
    }
}



val defaultMonthBody: @Composable (MonthModel, content: @Composable () -> Unit) -> Unit =
    { _, content -> content() }
val defaultWeekBody: (@Composable ColumnScope.(List<DayModel>, content: @Composable () -> Unit) -> Unit) =
    { _, content -> content() }


@Preview(showBackground = true)
@Composable
private fun DefaultMonthCalendarPreview() {
    val calendarState = rememberMonthCalendarState()
    MonthCalendar(
        monthModel = calendarState.getMonthModel(0),
        modifier = Modifier.size(width = 250.dp, height = 300.dp),
        dayBody = null,
        weekBody = null,
        monthBody = null
    )
}

@Preview(showBackground = true)
@Composable
private fun CustomizedMonthCalendarPreview() {
    val calendarState = rememberMonthCalendarState()
    MonthCalendar(
        monthModel = calendarState.getMonthModel(0),
        dayBody = { dayModel ->
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = dayModel.isOutDate.toString())
                Text(text = dayModel.date.toString())
                Text(text = dayModel.isToday.toString())
            }
        },
        weekBody = { _, content ->
            Box(
                Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "",
                    modifier = Modifier.size(30.dp),
                    tint = Color.Cyan
                )
                content()
            }
        },
        monthBody = { _, content ->
            Box {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "",
                    modifier = Modifier.size(100.dp),
                    tint = Color.Cyan
                )
                content()
            }
        },
    )
}