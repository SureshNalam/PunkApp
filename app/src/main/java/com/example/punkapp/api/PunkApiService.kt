package com.example.punkapp.api

import com.example.punkapp.domain.dto.BeersDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface PunkApiService {

    @GET("beers")
    suspend fun getBeers(): Response<List<BeersDto>>

    @GET("beers/{id}")
    suspend fun getBeerDetails(@Path("id")id: Int): Response<List<BeersDto>>

}