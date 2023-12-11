package com.example.punkapp.presentation.screens.beersList.mvi

import com.example.punkapp.domain.domainModels.BeersDomainModel

data class BeersScreenState(
    val beers: List<BeersDomainModel> = emptyList(),
    val isLoading: Boolean = false,
    val errorMsg: String? = null,
) {
    fun onBeersDataLoaded(beers: List<BeersDomainModel>): BeersScreenState {
        return copy(beers = beers, isLoading = false)
    }

    fun onError(errorMsg: String): BeersScreenState {
        return copy(isLoading = false, beers= emptyList() , errorMsg = errorMsg )
    }
}

sealed class BeersScreenIntent {
    object LoadBeers : BeersScreenIntent()
}