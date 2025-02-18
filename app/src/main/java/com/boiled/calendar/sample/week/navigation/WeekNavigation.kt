package com.boiled.calendar.sample.week.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.boiled.calendar.sample.Route
import com.boiled.calendar.sample.week.SampleWeekCalendarScreen
import kotlinx.serialization.Serializable

internal fun NavGraphBuilder.weekNavGraph(
    navigateToDesignatedCalendar: (Route) -> Unit,
) {
    composable<SampleWeek> {
        SampleWeekCalendarScreen(
            
        )
    }
}

@Serializable
internal data object SampleWeek: Route