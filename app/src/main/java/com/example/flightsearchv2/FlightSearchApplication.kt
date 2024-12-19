package com.example.flightsearchv2

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.flightsearchv2.data.UserPreferencesRepository
import com.example.flightsearchv2.di.AppContainer
import com.example.flightsearchv2.di.AppDataContainer

private const val SEARCHING_PREFERENCE_NAME = "searching_preferences"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = SEARCHING_PREFERENCE_NAME
)

class FlightSearchApplication: Application() {

    lateinit var container: AppContainer
    lateinit var userPreferencesRepository: UserPreferencesRepository

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
        userPreferencesRepository = UserPreferencesRepository(dataStore)
    }
}