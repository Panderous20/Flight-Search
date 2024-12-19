package com.example.flightsearchv2.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.flightsearchv2.model.Airport
import com.example.flightsearchv2.model.Favorite
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Airport::class, Favorite::class], version = 1, exportSchema = false)
abstract class FlightDatabase: RoomDatabase() {

    abstract fun flightDao(): FlightDao

    companion object {
        @Volatile
        private var Instance:FlightDatabase? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getDatabase(context: Context): FlightDatabase {
            return Instance ?: synchronized(this){
                Room.databaseBuilder(
                    context,
                    FlightDatabase::class.java,
                    "fligt_database"
                )
                    .createFromAsset("database/flight_search.db")
                    .build()
                    .also { Instance = it }
            }
        }
    }

}