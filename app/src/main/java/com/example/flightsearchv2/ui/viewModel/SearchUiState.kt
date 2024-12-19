package com.example.flightsearchv2.ui.viewModel

import com.example.flightsearchv2.model.Airport
import com.example.flightsearchv2.model.Favorite

data class SearchUiState(
    val queryString: String = "",
    val airportList: List<Airport> = emptyList()

)
