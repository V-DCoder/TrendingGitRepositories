package com.example.data.dagger

import com.example.data.network.GitApi
import com.example.data.network.repository.ApiRepository
import com.example.data.network.repository.ApiRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideApiRepository(gitApi: GitApi): ApiRepository {
        return ApiRepositoryImpl(gitApi)
    }
}