package com.example.punkapp.domain.repository

import com.example.punkapp.data.RemoteDataSource
import com.example.punkapp.api.Result
import com.example.punkapp.domain.domainModels.BeersDomainModel
import com.example.punkapp.domain.mappers.toBeersDomainModel
import javax.inject.Inject

class BeersRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BeersRepository {
    override suspend fun fetchBeers(): Result<List<BeersDomainModel>> {
        return try {
            val beersData = remoteDataSource.getPunkBeersData()
            val domainModelList = mutableListOf<BeersDomainModel>()
            beersData?.forEach {
                domainModelList.add(toBeersDomainModel(it))
            }
            Result.Success(domainModelList.toList())
        } catch (e: Throwable) {
            Result.Error(e.toString())
        }

    }
}