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
import java.time.DayOfWeek
import java.util.Locale

@Composable
fun WeekdaysHeader(
    modifier: Modifier = Modifier,
    locale: Locale = Locale.US,
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
                text = day.getDisplayName(java.time.format.TextStyle.SHORT, locale),
                style = textStyle,
                color = if (day == DayOfWeek.SUNDAY) sundayColor else weekdayColor,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true, name = "Weekdays Header US")
@Composable
fun WeekDaysHeaderUsPreview1() {
    WeekdaysHeader(
        modifier = Modifier.fillMaxWidth(),
    )
}

@Preview(showBackground = true, name = "Weekdays Header KOR")
@Composable
fun WeekDaysHeaderKorPreview2() {
    WeekdaysHeader(
        modifier = Modifier.fillMaxWidth(),
        locale = Locale.KOREA
    )
}

@Preview(showBackground = true, name = "Weekdays Header CHN")
@Composable
fun WeekDaysHeaderChnPreview() {
    WeekdaysHeader(
        modifier = Modifier.fillMaxWidth(),
        locale = Locale.CHINA
    )
}
