package com.example.data.cache

import io.reactivex.Single

interface LocalRepositoryProvider {
    fun deleteLocalCache(): Single<Int>


    fun insertRepositoryCache(
        repositoryList: List<RepositoryCacheEntity>,
        timeStamp: Long
    ): Single<Long>

    fun checkCacheExpired(currentTimestamp: Long): Single<Boolean>

    fun getRepositoryFromCache(): Single<List<RepositoryCacheEntity>>
}