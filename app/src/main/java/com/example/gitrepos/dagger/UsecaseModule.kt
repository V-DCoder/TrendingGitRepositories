package com.example.gitrepos.dagger

import com.example.data.DataManager
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
        datamanager: DataManager, threshold: Long, @Named("ioScheduler") ioScheduler: Scheduler,
        @Named("mainThreadScheduler") mainThreadScheduler: Scheduler
    ): FetchRepositoryUsecase {
        return FetchRepositoryUsecaseImpl(datamanager, threshold, ioScheduler, mainThreadScheduler)
    }

    @Provides
    fun provideCacheExpiryThreshold(): Long = 1000 * 60 * 2
}