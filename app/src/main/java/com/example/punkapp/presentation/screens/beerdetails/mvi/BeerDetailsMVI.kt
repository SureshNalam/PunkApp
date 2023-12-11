package com.example.punkapp.presentation.screens.beerdetails.mvi

import com.example.punkapp.domain.domainModels.BeersDomainModel

data class BeerDetailsState(
    val beerDetails: BeersDomainModel? = null,
    val isLoading:Boolean = false,
    val errorMessage:String? = null
) {

    fun onBeerDetailsDataLoaded(beerDetails: BeersDomainModel): BeerDetailsState {
        return copy(beerDetails = beerDetails, isLoading = false)
    }

    fun onErrorOccurred(errorMsg: String): BeerDetailsState {
        return copy(beerDetails = null, isLoading = false, errorMessage = errorMsg )
    }

}

sealed class BeerDetailsScreenIntent {

    object LoadBeerDetails: BeerDetailsScreenIntent()
}