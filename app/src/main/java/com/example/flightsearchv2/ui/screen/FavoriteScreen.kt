package com.example.flightsearchv2.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.flightsearchv2.model.Airport
import com.example.flightsearchv2.model.Favorite

@Composable
fun FavoriteScreen(
    airportList: List<Airport> = emptyList(),
    favoriteList: List<Favorite> = emptyList(),
    onFavoriteClick: (String, String) -> Unit
){
    if(favoriteList.isEmpty()){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
               text = "No favorite found"
            )
        }
    } else {
        LazyColumn(

        ) {
            items(favoriteList, key = { it.id }) { item ->
                val departAirport = airportList.firstOrNull { airport -> airport.code == item.departureCode }
                val destinationAirport =
                    airportList.firstOrNull { airport -> airport.code == item.destinationCode }
                if (departAirport == null || destinationAirport == null) {
                    Log.e("FavoriteScreen", "Invalid favorite: $item")
                }
                FlightDetail(
                    isFavorite = true,
                    departureCode = departAirport?.code ?: "",
                    departureName = departAirport?.name ?: "",
                    destinationCode = destinationAirport?.code ?: "",
                    destinationName = destinationAirport?.name ?: "",
                    onFavoriteClick = onFavoriteClick,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}