package com.boiled.calendar.compose.component.calendarbody

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
import com.boiled.calendar.compose.monthcalendar.rememberMonthCalendarState
import com.boiled.calendar.core.DayModel
import com.boiled.calendar.core.MonthModel
import com.boiled.calendar.core.util.ifNull


@Composable
internal fun MonthBody(
    monthModel: MonthModel,
    modifier: Modifier = Modifier,
    verticalInnerPadding: Dp = 0.dp,
    horizontalInnerPadding: Dp = 0.dp,
    weekBody: (@Composable ColumnScope.(List<DayModel>, content: @Composable () -> Unit) -> Unit)?,
    dayBody: (@Composable RowScope.(DayModel) -> Unit)?,
) {
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
                                modifier = Modifier.weight(1f).fillMaxHeight()
                            )
                    }
                }
            }
        }
    }

}

// Default implementation of Composable Bodies if not provided
private val defaultWeekBody: (@Composable ColumnScope.(List<DayModel>, content: @Composable () -> Unit) -> Unit) =
    { _, content -> content() }

@Preview(showBackground = true)
@Composable
private fun DefaultMonthCalendarPreview() {
    val calendarState = rememberMonthCalendarState()
    MonthBody(
        monthModel = calendarState.currentMonthModel,
        modifier = Modifier.size(width = 250.dp, height = 300.dp),
        dayBody = null,
        weekBody = null,
    )
}

@Preview(showBackground = true)
@Composable
private fun CustomizedMonthCalendarPreview() {
    val calendarState = rememberMonthCalendarState()
    MonthBody(
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
    )
}