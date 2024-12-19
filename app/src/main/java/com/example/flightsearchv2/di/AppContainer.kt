package com.example.flightsearchv2.di

import android.content.Context
import com.example.flightsearchv2.data.FlightDatabase
import com.example.flightsearchv2.data.FlightRepository
import com.example.flightsearchv2.data.OfflineRepository

interface AppContainer {
    val flightRepository: FlightRepository
}

class AppDataContainer(private val context: Context): AppContainer {

    override val flightRepository: FlightRepository by lazy {
        OfflineRepository(FlightDatabase.getDatabase(context).flightDao())
    }
}