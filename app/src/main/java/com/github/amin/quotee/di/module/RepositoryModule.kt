package com.github.amin.quotee.di.module

import com.github.amin.quotee.data.remote.ApiService
import com.github.amin.quotee.data.repository.AppRepository
import com.github.amin.quotee.data.repository.AppRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideRepository(apiService: ApiService): AppRepository {
        return AppRepositoryImpl(apiService)
    }
}