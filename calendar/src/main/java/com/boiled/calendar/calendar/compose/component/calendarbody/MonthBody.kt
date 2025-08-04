package com.boiled.calendar.calendar.compose.component.calendarbody

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.boiled.calendar.calendar.compose.monthcalendar.rememberMonthCalendarState
import com.boiled.calendar.calendar.model.DayModel
import com.boiled.calendar.calendar.model.MonthModel
import kotlinx.collections.immutable.toImmutableList

@Composable
internal fun MonthBody(
    monthModel: MonthModel,
    modifier: Modifier = Modifier,
    verticalInnerPadding: Dp = 0.dp,
    horizontalInnerPadding: Dp = 0.dp,
    weekBody: (@Composable ColumnScope.(List<DayModel>, content: @Composable () -> Unit) -> Unit)? = null,
    dayBody: (@Composable RowScope.(DayModel) -> Unit)? = null,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(verticalInnerPadding)
    ) {
        monthModel.calendarMonth.forEach { week ->
            weekBody?.invoke(this, week) {
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(horizontalInnerPadding)
                ) {
                    week.forEach { day ->
                        DayContent(dayBody, day)
                    }
                }
            } ?: WeekBody(
                week = week.toImmutableList(),
                modifier = Modifier.weight(1f)
            ) { day ->
                DayContent(dayBody, day)
            }
        }
    }
}

@Composable
private fun RowScope.DayContent(
    dayBody: (@Composable RowScope.(DayModel) -> Unit)?,
    dayModel: DayModel,
) {
    dayBody?.invoke(this, dayModel) ?: DefaultDayBody(
        dayModel = dayModel,
        modifier = Modifier
            .weight(1f)
            .fillMaxHeight()
    )
}

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
                    imageVector = Icons.Filled.Home,
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(),
                    tint = Color.Cyan
                )
                content()
            }
        },
    )
}