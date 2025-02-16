package com.boiled.calendar.compose.component.calendarbody

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.boiled.calendar.compose.component.calendarbody.util.DayBodyColor
import com.boiled.calendar.core.DayModel
import com.boiled.calendar.core.MonthModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import java.time.YearMonth

@Composable
fun DefaultWeekBody(
    week: ImmutableList<DayModel>,
    modifier: Modifier = Modifier,
    dayBody: (@Composable (DayModel) -> Unit)? = null,
) {
    Row(
        modifier = modifier,
    ) {
        week.forEach { day ->
            Box(
                modifier = modifier
                    .weight(1f),
                contentAlignment = Alignment.TopCenter
            ) {
                dayBody?.invoke(day)
                    ?: DefaultDayBody(
                        dayModel = day,
                        modifier = Modifier
                            .size(22.dp),
                        dayBodyColor = DayBodyColor.dayBodyColorDefault,
                    )
            }
        }

    }
}
@Preview(showBackground = true)
@Composable
private fun WeekBodyPreview() {
    val monthModel = MonthModel(YearMonth.now())
    DefaultWeekBody(
        week = monthModel.calendarMonth[0].toImmutableList(),
        modifier = Modifier.fillMaxWidth(),
    )
}