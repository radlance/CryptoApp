package com.radlance.cryptoapp.di

import com.radlance.data.network.api.CryptoApiClient
import com.radlance.data.network.api.CryptoApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    fun provideService(): CryptoApiService {
        return CryptoApiClient.service(CryptoApiService::class.java)
    }
}