package com.boiled.calendar.compose.component.calendarbody

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.boiled.calendar.compose.component.calendarbody.util.CalendarColor
import com.boiled.calendar.core.DayModel
import java.time.LocalDate

/**
 * Customizable Composable for showing Day cell on calendar.
 *
 * This composable is used in WeekBody to show days in weeks and months
 *
 * @param dayModel DayModel to show in current cell.
 * @param modifier Modifier for this composable.
 * @param textStyle TextStyle of number in current cell.
 * @param dayBodyColor DayBodyColor to customize this cell.
 */

@Composable
fun DefaultDayBody(
    dayModel: DayModel,
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape = CircleShape,
    textStyle: TextStyle = MaterialTheme.typography.labelSmall,
    dayBodyColor: CalendarColor = CalendarColor.dayBodyColorDefault,
) {
    val backgroundColor = remember(dayModel) {
        if (dayModel.isOutDate) dayBodyColor.outDateContainerColor
        else if (dayModel.isToday) dayBodyColor.todayContainerColor
        else dayBodyColor.containerColor
    }

    val textColor = remember(dayModel) {
        if (dayModel.isOutDate) dayBodyColor.outDateContentColor
        else if (dayModel.isToday) dayBodyColor.todayContentColor
        else dayBodyColor.contentColor
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopCenter
    ) {
        Box(
            modifier = Modifier
                .size(22.dp)
                .background(
                    color = backgroundColor,
                    shape = shape,
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = dayModel.date.dayOfMonth.toString(),
                color = textColor,
                style = textStyle,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DayBodyPreview() {
    Row(
        modifier = Modifier.height(100.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        DefaultDayBody(
            dayModel = DayModel(date = LocalDate.now(), isOutDate = false),
        )
        DefaultDayBody(
            dayModel = DayModel(date = LocalDate.now(), isOutDate = true),
        )
        DefaultDayBody(
            dayModel = DayModel(date = LocalDate.now(), isOutDate = true),
        )

    }
}