package com.boiled.calendar.calendar.model

import androidx.compose.runtime.Stable
import com.boiled.calendar.calendar.util.getFormattedString
import kotlinx.datetime.LocalDate

@Stable
data class DayModel(
    val date: LocalDate,
    val isToday: Boolean,
    val isOutDate: Boolean,
) {
    val scrapMapKey: String = date.getFormattedString()
}