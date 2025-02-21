package com.boiled.calendar.calendar.model.util

/**
 * Method to check if element is null. If yes, it returns default element given as parameter
 */
inline fun <T> T?.ifNull(defaultValue: T): T = this ?: defaultValue