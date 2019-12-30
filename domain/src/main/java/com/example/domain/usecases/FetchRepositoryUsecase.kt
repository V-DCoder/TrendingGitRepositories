package com.example.domain.usecases

import com.example.domain.entity.Repository
import io.reactivex.Single

interface FetchRepositoryUsecase {

    fun fetchGitRepositories(): Single<List<Repository>>
}