package com.example.data.network

import com.example.data.network.models.Repository
import io.reactivex.Single
import retrofit2.http.GET

interface GitApi {

    //    @GET("/repositories?language=java&since=daily")
    @GET("/repositories")
    fun getRepositories(): Single<List<Repository>>
}