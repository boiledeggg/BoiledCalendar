package com.boiled.calendar.calendar.compose.component.calendarbody

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.boiled.calendar.calendar.model.DayModel
import com.boiled.calendar.calendar.model.MonthModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import java.time.YearMonth

@Composable
internal fun WeekBody(
    week: ImmutableList<DayModel>,
    modifier: Modifier = Modifier,
    horizontalInnerPadding: Dp = 0.dp,
    dayBody: (@Composable RowScope.(DayModel) -> Unit)?,
) {
    Row(
        modifier = modifier,
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

@Preview(showBackground = true)
@Composable
private fun WeekBodyPreview() {
    val monthModel = MonthModel(YearMonth.now())
    WeekBody(
        week = monthModel.calendarMonth[0].toImmutableList(),
        dayBody = null,
        modifier = Modifier.height(200.dp),
    )
}