package com.boiled.calendar.calendar.component.calendarbody.util

import androidx.compose.ui.graphics.Color

data class CalendarColor(
    val containerColor: Color,
    val contentColor: Color,
    val selectedContainerColor: Color,
    val selectedContentColor: Color,
    val outDateContentColor: Color,
    val outDateContainerColor: Color,
    val todayContentColor: Color,
    val todayContainerColor: Color,
    val sundayContentColor: Color,
) {
    companion object {
        val dayBodyColorDefault = CalendarColor(
            containerColor = Color.Transparent,
            contentColor = Color.Black,
            selectedContainerColor = Color.Green,
            selectedContentColor = Color.White,
            outDateContentColor = Color.LightGray,
            outDateContainerColor = Color.Transparent,
            todayContentColor = Color.Black,
            todayContainerColor = Color.LightGray,
            sundayContentColor = Color.Red,
        )
    }
}
