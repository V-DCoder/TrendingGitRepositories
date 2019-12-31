package com.example.domain.usecases

import com.example.data.network.repository.ApiRepository
import com.example.domain.entity.Repository
import com.example.domain.mapper.GitRepositoryMapper
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

class FetchRepositoryUsecaseImpl @Inject constructor(
    private val apiRepository: ApiRepository,
    private val ioScheduler: Scheduler,
    private val uiScheduler: Scheduler
) : FetchRepositoryUsecase {
    override fun fetchGitRepositories(): Single<List<Repository>> {
        return apiRepository.getRepositories()
            .map { GitRepositoryMapper().mapNetwornRepositoryToDomain(it) }
            .observeOn(uiScheduler)
            .subscribeOn(ioScheduler)
    }
}