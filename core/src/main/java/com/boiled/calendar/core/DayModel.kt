package com.boiled.calendar.core

import androidx.compose.runtime.Stable
import com.boiled.calendar.core.util.getFormattedString
import java.time.LocalDate

@Stable
data class DayModel(
    val date: LocalDate,
    val isOutDate: Boolean,
) {
    val isToday: Boolean = date == LocalDate.now()

    val scrapMapKey: String = date.getFormattedString("yyyy-MM-dd")
}