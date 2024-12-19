package com.example.flightsearchv2.ui.viewModel

import com.example.flightsearchv2.model.Airport
import com.example.flightsearchv2.model.Favorite

data class FlightUiState(
    val code: String = "",
    val favoriteList: List<Favorite> = emptyList(),
    val airportList: List<Airport> = emptyList(),
    val airport: Airport = Airport()
)
