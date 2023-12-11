package com.example.punkapp.di

import com.example.punkapp.domain.repository.BeerDetailsRepository
import com.example.punkapp.domain.repository.BeerDetailsRepositoryImpl
import com.example.punkapp.domain.repository.BeersRepository
import com.example.punkapp.domain.repository.BeersRepositoryImpl
import com.example.punkapp.api.PunkApiService
import com.example.punkapp.data.RemoteDataSource
import com.example.punkapp.data.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesBeersDataSource(punkApiService: PunkApiService): RemoteDataSource {
        return RemoteDataSourceImpl(punkApiService)
    }

    @Provides
    @Singleton
    fun providesBeersRepository(remoteDataSource: RemoteDataSource): BeersRepository {
        return BeersRepositoryImpl(remoteDataSource)
    }

   @Provides
   @Singleton
   fun provideBeerDetailsRepository(remoteDataSource: RemoteDataSource): BeerDetailsRepository {
       return BeerDetailsRepositoryImpl(remoteDataSource)
   }
}