package com.boiled.calendar.sample.month.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.boiled.calendar.sample.Route
import com.boiled.calendar.sample.month.SampleMonthCalendarA
import com.boiled.calendar.sample.month.SampleMonthCalendarB
import com.boiled.calendar.sample.month.SampleMonthCalendarScreen
import kotlinx.collections.immutable.toImmutableList
import kotlinx.serialization.Serializable

internal fun NavGraphBuilder.monthNavGraph(
    navigateToDesignatedCalendar: (Route) -> Unit,
) {
    composable<SampleMonth> {
        SampleMonthCalendarScreen(
            routes = listOf(
                SampleMonthA,
                SampleMonthB
            ).toImmutableList(),
            onClick = navigateToDesignatedCalendar
        )
    }

    composable<SampleMonthA> {
        SampleMonthCalendarA()
    }

    composable<SampleMonthB> {
        SampleMonthCalendarB()
    }
}

@Serializable
internal data object SampleMonth: Route
@Serializable
internal data object SampleMonthA: Route
@Serializable
internal data object SampleMonthB: Route