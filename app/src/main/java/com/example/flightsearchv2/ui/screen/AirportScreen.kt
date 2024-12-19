package com.example.flightsearchv2.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flightsearchv2.data.MockData
import com.example.flightsearchv2.model.Airport
import com.example.flightsearchv2.model.Favorite
import com.example.flightsearchv2.ui.theme.FlightSearchV2Theme

@Composable
fun AirportScreen(
    departure: Airport,
    airportList: List<Airport>,
    favoriteList: List<Favorite>,
    onFavoriteClick: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(airportList, key = { it.id }) { airport ->
            val isFavorite = favoriteList.find { f ->
                f.departureCode == departure.code &&
                        f.destinationCode == airport.code }

            FlightDetail(
                isFavorite = isFavorite != null,
                departureCode = departure.code,
                departureName = departure.name,
                destinationCode = airport.code,
                destinationName = airport.name,
                onFavoriteClick = onFavoriteClick,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
@Preview
@Composable
fun AirportScreenPreview() {
    FlightSearchV2Theme {
        AirportScreen(
            MockData.airports[0],
            MockData.airports,
            emptyList(),
            onFavoriteClick = { _: String, _:String ->}
        )
    }
}