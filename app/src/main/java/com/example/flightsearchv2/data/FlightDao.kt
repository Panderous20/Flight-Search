package com.example.flightsearchv2.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.flightsearchv2.model.Airport
import com.example.flightsearchv2.model.Favorite
import kotlinx.coroutines.flow.Flow

@Dao
interface FlightDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Favorite)
    @Delete
    suspend fun deleteFavorite(favorite: Favorite)

    //Airport

    // Retrive Airport list
    @Query("SELECT * FROM airport ORDER BY name ASC")
    suspend fun getAllAirport(): List<Airport>

    // Retrive Airport list by id to show up arrive place
    @Query("""
        SELECT * FROM airport
    """)
    fun getAllAirportByFlow(): Flow<List<Airport>>

    // Retrive Airport list by query while searching departure
    @Query("""
        SELECT * FROM airport
        WHERE iata_code LIKE '%' || :query || '%'
        OR name LIKE '%' || :query || '%' 
        ORDER BY passengers DESC
    """)
    fun getAllAirportByQuery(query: String): Flow<List<Airport>>

    // Retrive departure by code
    @Query("""
        SELECT *  FROM airport 
        WHERE iata_code = :code
    """)
    suspend fun getAirportByCode(code: String): Airport

    // Retrive destination list by code
    @Query("""
        SELECT * FROM airport
        WHERE NOT iata_code = :code
        ORDER BY passengers DESC
    """)
    suspend fun getAirportListByCode(code: String): List<Airport>


    // Favorite
    @Query("""
        SELECT * FROM favorite
    """)
    fun getALLFavoriteByFlow(): Flow<List<Favorite>>

    @Query("""
        SELECT * FROM favorite
    """)
    suspend fun getALLFavorite(): List<Favorite>

    @Query("""
        SELECT * FROM favorite
        WHERE departure_code = :departureCode AND destination_code = :destinationCode
    """)
    suspend fun getSingleFavorite(departureCode: String, destinationCode: String): Favorite?
}