package com.example.data.cache

import android.util.Log
import io.reactivex.Single
import javax.inject.Inject

class LocalRepositoryProviderImpl @Inject constructor(private val localCache: LocalCache) :
    LocalRepositoryProvider {

    override fun deleteLocalCache(): Single<Int> {
        Log.w("insertRepositoryCache", "deleting at ${System.currentTimeMillis()}")
        return localCache.repositoryDAO().deleteTimestamp()
            .flatMap { localCache.repositoryDAO().deleteRepositoryCache() }

    }


    override fun insertRepositoryCache(
        repositoryList: List<RepositoryCacheEntity>,
        timeStamp: Long
    ): Single<Long> {
        Log.w("insertRepositoryCache", "inserting in db at $timeStamp")
        return localCache.repositoryDAO().insertRepositoryJson(repositoryList)
            .flatMap { localCache.repositoryDAO().insertTimestamp(SyncTimestamp(timeStamp)) }
    }

    override fun checkCacheExpired(currentTimestamp: Long): Single<Boolean> {
        Log.w("insertRepositoryCache", "checking valid entries at $currentTimestamp")
        return localCache.repositoryDAO().searchValidTimestamp(currentTimestamp)
            .map { it < 1 }
    }

    override fun getRepositoryFromCache(): Single<List<RepositoryCacheEntity>> {
        return localCache.repositoryDAO().getRepositoryFromCache()
    }
}