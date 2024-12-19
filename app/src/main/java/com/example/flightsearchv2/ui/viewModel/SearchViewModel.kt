package com.example.flightsearchv2.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.flightsearchv2.FlightSearchApplication
import com.example.flightsearchv2.data.FlightRepository
import com.example.flightsearchv2.data.UserPreferencesRepository
import com.example.flightsearchv2.model.Airport
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    val flightRepository: FlightRepository,
    private val userPreferencesRepository: UserPreferencesRepository
): ViewModel() {

    private val _searchUiState = MutableStateFlow(SearchUiState())
    val searchUiState: StateFlow<SearchUiState> = _searchUiState.asStateFlow()

    init {
        getAirportList()
        viewModelScope.launch {
            userPreferencesRepository.searchString
                .distinctUntilChanged() // Tránh collect các giá trị trùng lặp
                .collect{
                processSearchQuery(it)
            }
        }
    }


    fun getAirportList() {
        viewModelScope.launch {
            _searchUiState.update {
                _searchUiState.value.copy(airportList = flightRepository.getAllAirport())
            }
        }
    }

    fun processSearchQuery(query: String) {
        viewModelScope.launch {
            if(query.isNotEmpty()) {
                _searchUiState.update {
                    _searchUiState.value.copy(queryString = query)
                }
            }
        }
    }

    //update search query
    fun updateQuery(query: String) {
        _searchUiState.update {
            _searchUiState.value.copy(queryString = query)
        }
        updatePreferencesSearchString(query)
    }


    // retrive suggestList from database by flightRepository
    val suggestList: StateFlow<List<Airport>> = _searchUiState.map { it.queryString }
        .flatMapLatest { query ->
            flightRepository.getAllAirportByQueryFlow(query)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )

    fun updatePreferencesSearchString(string: String) {
        viewModelScope.launch {
            userPreferencesRepository.saveSearchPreferences(string)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FlightSearchApplication)
                SearchViewModel(
                    flightRepository = application.container.flightRepository,
                    userPreferencesRepository = application.userPreferencesRepository
                )
            }
        }
    }
}