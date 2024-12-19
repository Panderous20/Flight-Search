package com.example.flightsearchv2.data

import androidx.room.Query
import com.example.flightsearchv2.model.Airport
import com.example.flightsearchv2.model.Favorite
import kotlinx.coroutines.flow.Flow

class OfflineRepository(private val flightDao: FlightDao): FlightRepository {
    override suspend fun insertFavorite(favorite: Favorite) {
        return flightDao.insertFavorite(favorite)
    }
    override suspend fun deleteFavorite(favorite: Favorite) {
        return flightDao.deleteFavorite(favorite)
    }

    // Airport
    override fun getAllAirportByQueryFlow(query: String): Flow<List<Airport>> {
        return flightDao.getAllAirportByQuery(query)
    }

    override suspend fun getAirportByCode(code: String): Airport {
        return flightDao.getAirportByCode(code)
    }

    override suspend fun getAirportListByCode(code: String): List<Airport> {
        return flightDao.getAirportListByCode(code)
    }

    override suspend fun getAllAirport(): List<Airport> {
        return flightDao.getAllAirport()
    }

    // Favorite
    override fun getAllFavoriteByFlow(): Flow<List<Favorite>> {
        return flightDao.getALLFavoriteByFlow()
    }

    override suspend fun getAllFavorite(): List<Favorite> {
        return flightDao.getALLFavorite()
    }

    override suspend fun getSingleFavorite(
        departureCode: String,
        destinationCode: String
    ): Favorite? {
        return flightDao.getSingleFavorite(departureCode, destinationCode)
    }
}