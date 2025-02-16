package com.boiled.calendar.compose.component.calendarbody

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.boiled.calendar.core.DayModel
import com.boiled.calendar.core.MonthModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import java.time.YearMonth

@Composable
fun DefaultMonthBody(
    monthModel: MonthModel,
    modifier: Modifier = Modifier,
    containerColor: Color = Color.White,
    contentPadding: PaddingValues? = null,
    weekBody: (@Composable (ImmutableList<DayModel>, (@Composable (DayModel) -> Unit)?) -> Unit)? = null,
    dayBody: (@Composable (DayModel) -> Unit)? = null
) {
    Column(
        modifier = modifier
            .background(color = containerColor)
            .padding(contentPadding ?: PaddingValues())
    ) {
        monthModel.calendarMonth.forEach { week ->
            weekBody?.invoke(week.toImmutableList(), dayBody)
                ?: DefaultWeekBody(
                    week = week.toImmutableList(),
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    dayBody
                )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MonthBodyPreview() {
    DefaultMonthBody(
        monthModel = MonthModel(YearMonth.now()),
        modifier = Modifier.height(300.dp)
    )
}