package com.example.punkapp.data

import com.example.punkapp.domain.dto.BeersDto


interface RemoteDataSource {
    suspend fun getPunkBeersData(): List<BeersDto>?
    suspend fun getBeerDetails(id: Int): List<BeersDto>?
}