package com.boiled.calendar.calendar.model

import androidx.compose.runtime.Stable
import com.boiled.calendar.calendar.util.getFormattedString
import java.time.LocalDate

@Stable
data class DayModel(
    val date: LocalDate,
    val isOutDate: Boolean,
) {
    val isToday: Boolean = date == LocalDate.now()

    val scrapMapKey: String = date.getFormattedString("yyyy-MM-dd")
}