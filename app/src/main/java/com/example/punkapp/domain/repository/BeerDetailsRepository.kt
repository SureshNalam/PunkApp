package com.example.punkapp.domain.repository

import com.example.punkapp.api.Result
import com.example.punkapp.domain.domainModels.BeersDomainModel

interface BeerDetailsRepository {
    suspend fun fetchBeerDetails(id: Int): Result<List<BeersDomainModel>>
}