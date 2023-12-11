package com.example.punkapp.data

import com.example.punkapp.api.PunkApiService
import com.example.punkapp.domain.dto.BeersDto
import javax.inject.Inject


class RemoteDataSourceImpl @Inject constructor(
    private val punkApiService: PunkApiService
): RemoteDataSource {
    override suspend fun getPunkBeersData(): List<BeersDto>? {
        val response = punkApiService.getBeers()
        return response.body()
    }

    override suspend fun getBeerDetails(id: Int): List<BeersDto>? {
        val response = punkApiService.getBeerDetails(id)
        return response.body()
    }

}