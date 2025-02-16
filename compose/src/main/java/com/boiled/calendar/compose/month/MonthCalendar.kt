package com.boiled.calendar.compose.month

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.boiled.calendar.core.MonthModel
import java.util.Locale

@Composable
fun LazyListScope.MonthCalendar(
    monthModel: MonthModel,
    modifier: Modifier,
    monthHeader: @Composable (Locale, Modifier) -> Unit?,
    weekBody: @Composable (MonthModel, Modifier) -> Unit,
) {

}