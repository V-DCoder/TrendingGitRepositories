package com.example.data

import com.example.data.cache.LocalRepositoryProvider
import com.example.data.network.repository.ApiRepository

class DataManagerImpl constructor(
    private val localRepositoryProvider: LocalRepositoryProvider,
    private val apiRepository: ApiRepository
) : DataManager {
    override fun getLocalRepositoryProvider(): LocalRepositoryProvider {
        return localRepositoryProvider
    }

    override fun getAPIRepository(): ApiRepository {
        return apiRepository
    }
}