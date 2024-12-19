package com.example.flightsearchv2

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.flightsearchv2.ui.screen.MainScreen
import com.example.flightsearchv2.ui.theme.FlightSearchV2Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightSearchApp() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.flight_search),
                        color = Color.White
                    )
                },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary)
            )
        }
    ) { innerpadding ->
        MainScreen(
            modifier = Modifier.fillMaxSize()
                .padding(innerpadding)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FlightSearchAppPreview() {
    FlightSearchV2Theme {
        FlightSearchApp()
    }
}

