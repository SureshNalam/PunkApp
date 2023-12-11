package com.example.punkapp.presentation.screens.beersList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.punkapp.domain.repository.BeersRepository
import com.example.punkapp.api.Result
import com.example.punkapp.domain.domainModels.BeersDomainModel
import com.example.punkapp.presentation.screens.beersList.mvi.BeersScreenIntent
import com.example.punkapp.presentation.screens.beersList.mvi.BeersScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BeersViewModel @Inject constructor(
    private val beersRepository: BeersRepository
) : ViewModel() {

    private val _state = MutableStateFlow(BeersScreenState(isLoading = true))
    val state: StateFlow<BeersScreenState> = _state.asStateFlow()

    init {
        processIntent(BeersScreenIntent.LoadBeers)
    }

    private fun processIntent(beersScreenIntent: BeersScreenIntent) {
        when (beersScreenIntent) {
            is BeersScreenIntent.LoadBeers -> {
                viewModelScope.launch {
                    val response = beersRepository.fetchBeers()
                    processResult(response)
                }
            }
        }
    }

    private fun processResult(response: Result<List<BeersDomainModel>>) {
        when (response) {
            is Result.Success -> {
                setState {
                    onBeersDataLoaded(beers = response.data)
                }
            }

            is Result.Error -> {
                setState {
                    onError(errorMsg = response.exception)
                }
            }
        }
    }

    private fun setState(stateReducer: BeersScreenState.() -> BeersScreenState) {
        viewModelScope.launch {
            _state.emit(stateReducer(state.value))
        }
    }
}