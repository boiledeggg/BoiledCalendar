package com.boiled.calendar.sample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.boiled.calendar.sample.month.navigation.SampleMonth
import com.boiled.calendar.sample.month.navigation.monthNavGraph
import com.boiled.calendar.sample.week.navigation.SampleWeek
import com.boiled.calendar.sample.week.navigation.weekNavGraph
import kotlinx.serialization.Serializable

@Composable
internal fun SampleScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = SampleCalendar,
        modifier = modifier,
    ) {
        composable<SampleCalendar> {
            SampleCalendarScreen(
                onMonthNavigate = { navController.navigate(SampleMonth) },
                onWeekNavigate = { navController.navigate(SampleWeek) }
            )
        }

        monthNavGraph (
            navigateToDesignatedCalendar = navController::navigate
        )

        weekNavGraph(
            navigateToDesignatedCalendar = {}
        )
    }
}


@Composable
private fun SampleCalendarScreen(
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

internal interface Route

@Serializable
internal data object SampleCalendar: Route
