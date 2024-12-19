package com.example.flightsearchv2.ui.viewModel

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.flightsearchv2.FlightSearchApplication
import com.example.flightsearchv2.data.FlightRepository
import com.example.flightsearchv2.model.Airport
import com.example.flightsearchv2.model.Favorite
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FlightViewModel(
    val flightRepository: FlightRepository
): ViewModel() {
    private val _flightUiState = MutableStateFlow(FlightUiState())
    val flightUiState = _flightUiState.asStateFlow()

    private val _flightAdd = MutableStateFlow<Boolean?>(null)
    val flightAdd = _flightAdd.asStateFlow()

    init{
        baseData()
    }

    private fun baseData() {
        viewModelScope.launch {
            _flightUiState.update {
                _flightUiState.value.copy(
                    favoriteList = flightRepository.getAllFavorite().toMutableStateList(),
                    airportList = flightRepository.getAllAirport()
                )
            }
        }
    }

    fun updateDepartureCode(code: String) {
        viewModelScope.launch{
            _flightUiState.update {
                _flightUiState.value.copy(
                    code = code,
                    airport = getAirportByCode(code),
                    airportList = getAllAirportByCode(code)
                )
            }
        }
    }
    suspend fun getAirportByCode(code: String): Airport {
        return flightRepository.getAirportByCode(code)
    }

    suspend fun getAllAirportByCode(code: String): List<Airport> {
        return flightRepository.getAirportListByCode(code)
    }

    fun addFavorite(departureCode: String, destinationCode: String) {
        viewModelScope.launch {
            val favorite: Favorite? = flightRepository.getSingleFavorite(departureCode, destinationCode)

            if(favorite == null){
                val temp = Favorite(
                    departureCode = departureCode,
                    destinationCode = destinationCode
                )
                flightRepository.insertFavorite(temp)
                _flightAdd.value = true
            } else {
                flightRepository.deleteFavorite(favorite)
                _flightAdd.value = false
            }

            getAllFavorite()
        }
    }
    fun getAllFavorite() {
        viewModelScope.launch {
            flightRepository.getAllFavoriteByFlow().collect { favoriteList ->
                _flightUiState.update {
                    _flightUiState.value.copy(
                        favoriteList = favoriteList
                    )
                }
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FlightSearchApplication)
                FlightViewModel(
                    flightRepository = application.container.flightRepository
                )
            }
        }
    }
}