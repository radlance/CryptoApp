package com.radlance.cryptoapp.di

import com.radlance.data.repository.RepositoryImpl
import com.radlance.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {
    @Binds
    fun provideRepository(repositoryImpl: RepositoryImpl): Repository
}