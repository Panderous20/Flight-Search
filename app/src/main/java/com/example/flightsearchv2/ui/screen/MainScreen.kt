package com.example.flightsearchv2.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flightsearchv2.ui.viewModel.FlightViewModel
import com.example.flightsearchv2.ui.viewModel.SearchViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val searchViewModel: SearchViewModel = viewModel(factory = SearchViewModel.Factory)
    val searchUiState by searchViewModel.searchUiState.collectAsState()
    val suggestList by searchViewModel.suggestList.collectAsState()

    val flightViewModel: FlightViewModel = viewModel(factory = FlightViewModel.Factory)
    val flightUiState by flightViewModel.flightUiState.collectAsState()
    val flightAdd by flightViewModel.flightAdd.collectAsState()

    var chooseFlight by rememberSaveable { mutableStateOf(false) }
    val onFavoriteClick = {departureCode: String, destinationCode: String ->
        flightViewModel.addFavorite(departureCode, destinationCode)
    }

    // Show up notification
    LaunchedEffect(flightAdd) {
        flightAdd?.let {
            val message = if (it) "Added" else "Deleted"
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        SearchField(
            query = searchUiState.queryString,
            onValueChange = {
                searchViewModel.updateQuery(it)
            },
            modifier = Modifier.padding(8.dp)
        )
        if(searchUiState.queryString.isEmpty()) {

            chooseFlight = false
            Text(
                text = "Favorite routes",
            )
            FavoriteScreen(
                airportList = searchUiState.airportList,
                favoriteList = flightUiState.favoriteList,
                onFavoriteClick = onFavoriteClick
            )
        } else {
            if(chooseFlight) {
                Text(
                    text = "Flight from ${flightUiState.airport.code}",
                )
                AirportScreen(
                    departure = flightUiState.airport,
                    airportList = flightUiState.airportList,
                    favoriteList = flightUiState.favoriteList,
                    onFavoriteClick = onFavoriteClick
                )
            } else {
                SuggestScreen(
                    suggestList = suggestList,
                    onSelected = {
                        flightViewModel.updateDepartureCode(it)
                        chooseFlight = true
                    }
                )
            }
        }
    }
}