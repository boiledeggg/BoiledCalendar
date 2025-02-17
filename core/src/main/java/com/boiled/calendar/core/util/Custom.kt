package com.boiled.calendar.core.util

import androidx.compose.runtime.Composable
import com.boiled.calendar.core.MonthModel

/**
 * Method to check if element is null. If yes, it returns default element given as parameter
 */
inline fun <T> T?.ifNull(defaultValue: T): T = this ?: defaultValue