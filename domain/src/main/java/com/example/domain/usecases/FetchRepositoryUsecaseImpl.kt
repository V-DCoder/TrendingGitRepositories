package com.example.domain.usecases

import android.util.Log
import com.example.data.DataManager
import com.example.domain.entity.Repository
import com.example.domain.mapper.GitRepositoryMapper
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

class FetchRepositoryUsecaseImpl @Inject constructor(
    private val dataManager: DataManager,
    private val cacheExpireThreshold: Long,
    private val ioScheduler: Scheduler,
    private val uiScheduler: Scheduler
) : FetchRepositoryUsecase {
    override fun fetchGitRepositories(): Single<List<Repository>> {
        return hasCacheExpired().flatMap {
            fetchTemp(it)
        }
            .observeOn(uiScheduler)
            .subscribeOn(ioScheduler)
    }

    private fun hasCacheExpired(): Single<Boolean> {
        return dataManager.getLocalRepositoryProvider()
            .checkCacheExpired(System.currentTimeMillis() - cacheExpireThreshold)
    }


    private fun fetchTemp(isExpired: Boolean): Single<List<Repository>> {
        return if (isExpired) {
            deleteCache()
                .flatMap {
                    fetchFromServer()
                }.flatMap {
                    insertRepositoryInCache(it)
                }.flatMap {
                    fetchFromCache()
                }

        } else {
            fetchFromCache()
        }
    }

    private fun insertRepositoryInCache(list: List<Repository>): Single<Long> {
        return dataManager.getLocalRepositoryProvider()
            .insertRepositoryCache(
                GitRepositoryMapper().mapDomainToCacheRepository(list),
                System.currentTimeMillis()
            )
    }

    private fun fetchFromCache(): Single<List<Repository>> {
        return dataManager.getLocalRepositoryProvider().getRepositoryFromCache()
            .map { GitRepositoryMapper().mapCacheRepositoryToDomain(it) }
    }

    private fun fetchFromServer(): Single<List<Repository>> {
        return dataManager.getAPIRepository().getRepositories()
            .map {
                GitRepositoryMapper().mapNetwornRepositoryToDomain(it) }
    }

    private fun deleteCache(): Single<Int> {
        return dataManager.getLocalRepositoryProvider().deleteLocalCache()
    }



}