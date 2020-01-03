package com.example.data.dagger

import android.content.Context
import com.example.data.DataManager
import com.example.data.DataManagerImpl
import com.example.data.cache.LocalCache
import com.example.data.cache.LocalRepositoryProvider
import com.example.data.cache.LocalRepositoryProviderImpl
import com.example.data.network.GitApi
import com.example.data.network.repository.ApiRepository
import com.example.data.network.repository.ApiRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule(private val context: Context) {

    @Provides
    fun provideContext(): Context = context

    @Provides
    fun provideApiRepository(gitApi: GitApi): ApiRepository {
        return ApiRepositoryImpl(gitApi)
    }

    @Provides
    fun provideLocalCache(context: Context): LocalCache {
        return LocalCache.getLocalCache(context)
    }

    @Provides
    fun provideLocalRepositoryProvider(localCache: LocalCache): LocalRepositoryProvider {
        return LocalRepositoryProviderImpl(localCache)
    }

    @Singleton
    @Provides
    fun provideDataManager(
        localRepositoryProvider: LocalRepositoryProvider,
        apiRepository: ApiRepository
    ): DataManager = DataManagerImpl(localRepositoryProvider, apiRepository)
}