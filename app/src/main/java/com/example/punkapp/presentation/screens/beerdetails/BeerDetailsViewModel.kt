package com.example.punkapp.presentation.screens.beerdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.punkapp.domain.repository.BeerDetailsRepository
import com.example.punkapp.api.Result
import com.example.punkapp.domain.domainModels.BeersDomainModel
import com.example.punkapp.presentation.screens.beerdetails.mvi.BeerDetailsScreenIntent
import com.example.punkapp.presentation.screens.beerdetails.mvi.BeerDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BeerDetailsViewModel @Inject constructor(
    private val repository: BeerDetailsRepository,
): ViewModel() {

    private val _state = MutableStateFlow(BeerDetailsState(isLoading = true))
    val state: StateFlow<BeerDetailsState> = _state.asStateFlow()

    fun processIntent(beerDetailsScreenIntent: BeerDetailsScreenIntent, selectedBeerId: Int?) {
        when(beerDetailsScreenIntent){

            is BeerDetailsScreenIntent.LoadBeerDetails -> {
                viewModelScope.launch {
                    val response = selectedBeerId?.let { repository.fetchBeerDetails(it) }
                    if (response != null) {
                        processResult(response)
                    }
                }
            }
        }
    }

    private fun processResult(response: Result<List<BeersDomainModel>>) {
        when(response) {
            is Result.Success -> {
                setState {
                    onBeerDetailsDataLoaded(beerDetails = response.data[0])
                }
            }
            is Result.Error -> {
                setState {
                    onErrorOccurred(errorMsg = response.exception)
                }
            }
        }
    }


    private fun setState(stateReducer: BeerDetailsState.() -> BeerDetailsState) {
        viewModelScope.launch {
            _state.emit(stateReducer(state.value))
        }
    }
}