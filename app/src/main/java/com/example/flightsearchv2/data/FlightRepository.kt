package com.example.flightsearchv2.data

import androidx.room.Query
import com.example.flightsearchv2.model.Airport
import com.example.flightsearchv2.model.Favorite
import kotlinx.coroutines.flow.Flow

interface FlightRepository{
    suspend fun insertFavorite(favorite: Favorite)
    suspend fun deleteFavorite(favorite: Favorite)

    //Airport
    fun getAllAirportByQueryFlow(query: String): Flow<List<Airport>>
    suspend fun getAirportByCode(code: String): Airport
    suspend fun getAirportListByCode(code: String): List<Airport>
    suspend fun getAllAirport(): List<Airport>

    // Favorite
    suspend fun getSingleFavorite(departureCode: String, destinationCode: String): Favorite?
    suspend fun getAllFavorite(): List<Favorite>
    fun getAllFavoriteByFlow(): Flow<List<Favorite>>
}