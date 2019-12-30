package com.example.gitrepos.dagger

import com.example.data.network.repository.ApiRepository
import com.example.domain.usecases.FetchRepositoryUsecase
import com.example.domain.usecases.FetchRepositoryUsecaseImpl
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import javax.inject.Named

@Module
class UsecaseModule {


    @Provides
    fun providesFetchRepositoryUsecase(
        apiRepository: ApiRepository, @Named("ioScheduler") ioScheduler: Scheduler,
        @Named("mainThreadScheduler") mainThreadScheduler: Scheduler
    ): FetchRepositoryUsecase {
        return FetchRepositoryUsecaseImpl(apiRepository, ioScheduler, mainThreadScheduler)
    }
}