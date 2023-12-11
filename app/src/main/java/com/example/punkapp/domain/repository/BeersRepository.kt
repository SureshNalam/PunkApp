package com.example.punkapp.domain.repository

import com.example.punkapp.api.Result
import com.example.punkapp.domain.domainModels.BeersDomainModel

interface BeersRepository {
    suspend fun fetchBeers(): Result<List<BeersDomainModel>>
}