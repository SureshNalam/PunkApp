package com.example.punkapp.domain.repository

import com.example.punkapp.data.RemoteDataSource
import com.example.punkapp.api.Result
import com.example.punkapp.domain.domainModels.BeersDomainModel
import com.example.punkapp.domain.mappers.toBeersDomainModel
import javax.inject.Inject

class BeerDetailsRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BeerDetailsRepository {
    override suspend fun fetchBeerDetails(id: Int): Result<List<BeersDomainModel>> {
        return try {
            val beerDetails = remoteDataSource.getBeerDetails(id)
            val domainModelList = mutableListOf<BeersDomainModel>()
            beerDetails?.forEach {
                domainModelList.add(toBeersDomainModel(it))
            }
            Result.Success(domainModelList.toList())
        } catch (e: Throwable) {
            Result.Error(e.toString())
        }
    }
}