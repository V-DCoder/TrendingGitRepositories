package com.example.data.network.repository

import com.example.data.network.GitApi
import com.example.data.network.models.Repository
import io.reactivex.Single
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(val gitApi: GitApi) : ApiRepository {
    override fun getRepositories(): Single<List<Repository>> {
        return gitApi.getRepositories()
    }


}