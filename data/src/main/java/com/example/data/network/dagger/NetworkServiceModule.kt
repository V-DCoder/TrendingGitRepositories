package com.example.data.network.dagger

import com.example.data.network.GitApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named


@Module
class RetrofitGitService {

    @Provides
    fun provideGitApiService(@Named("BaseUrl") baseUrl: String): GitApi {
        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
            .create(GitApi::class.java)
    }

    @Provides
    @Named("BaseUrl")
    fun provideBaseUrl() : String {return "https://github-trending-api.now.sh"}
}