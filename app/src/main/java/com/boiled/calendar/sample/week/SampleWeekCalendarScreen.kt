package com.boiled.calendar.sample.week

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun SampleWeekCalendarScreen(
    modifier: Modifier = Modifier,
    onMonthNavigate: () -> Unit = {},
    onWeekNavigate: () -> Unit = {},
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically)
    ) {
        Button(
            onClick = onMonthNavigate
        ) {
            Text(text = "Move to Month Calendar Sample")
        }

        Button(
            onClick = onWeekNavigate
        ) {
            Text(text = "Move to Month Week Sample")
        }
    }
}
