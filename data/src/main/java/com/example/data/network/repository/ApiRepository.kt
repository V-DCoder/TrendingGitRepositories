package com.example.data.network.repository

import com.example.data.network.models.Repository
import io.reactivex.Single

interface ApiRepository {

    fun getRepositories(): Single<List<Repository>>
}