package com.boiled.calendar.sample.month

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.boiled.calendar.sample.Route
import kotlinx.collections.immutable.ImmutableList


@Composable
internal fun SampleMonthCalendarScreen(
    modifier: Modifier = Modifier,
    routes: ImmutableList<Route>,
    onClick: (Route) -> Unit = {},
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(routes) {  route ->
            Button(
                onClick = { onClick(route) }
            ) {
                Text(text = "Move to ${route::class.simpleName}")
            }
        }
    }
}
