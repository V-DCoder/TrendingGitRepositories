package com.example.data

import com.example.data.cache.LocalRepositoryProvider
import com.example.data.network.repository.ApiRepository

interface DataManager {

    fun getLocalRepositoryProvider(): LocalRepositoryProvider
    fun getAPIRepository(): ApiRepository
}