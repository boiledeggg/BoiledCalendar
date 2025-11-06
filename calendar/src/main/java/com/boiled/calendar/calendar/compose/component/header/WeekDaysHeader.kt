package com.boiled.calendar.calendar.compose.component.header

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.datetime.DayOfWeek

//Default WeekDays Header for Calendar.
//Weekday name is fixed in US locale.
@Composable
fun WeekdaysHeader(
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.labelSmall,
    sundayColor: Color = Color.Red,
    weekdayColor: Color = Color.Black,
    containerColor: Color = Color.White,
    contentPadding: PaddingValues = PaddingValues(),
) {
    Row(
        modifier = modifier
            .background(containerColor)
            .padding(contentPadding),
    ) {
        DayOfWeek.entries.forEach { day ->
            Text(
                modifier = Modifier.weight(1f),
                text = day.nameInUS(),
                style = textStyle,
                color = if (day == DayOfWeek.SUNDAY) sundayColor else weekdayColor,
                textAlign = TextAlign.Center
            )
        }
    }
}

internal fun DayOfWeek.nameInUS(upperCase: Boolean = false): String {
    var text: String = when (this) {
        DayOfWeek.MONDAY -> "mon"
        DayOfWeek.TUESDAY -> "tues"
        DayOfWeek.WEDNESDAY -> "wed"
        DayOfWeek.THURSDAY -> "thurs"
        DayOfWeek.FRIDAY -> "fri"
        DayOfWeek.SATURDAY -> "sat"
        DayOfWeek.SUNDAY -> "sun"
    }
    if (upperCase) text = text.uppercase()
    return text
}

@Preview(showBackground = true, name = "Weekdays Header US")
@Composable
fun WeekDaysHeaderUsPreview1() {
    WeekdaysHeader(
        modifier = Modifier.fillMaxWidth(),
    )
}
