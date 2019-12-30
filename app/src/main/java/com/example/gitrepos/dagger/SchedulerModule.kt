package com.example.gitrepos.dagger

import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
class SchedulerModule {

    @Provides
    @Singleton
    @Named("ioScheduler")
    internal fun provideIoScheduler() = Schedulers.io()

    @Provides
    @Singleton
    @Named("mainThreadScheduler")
    internal fun provideMainThreadScheduler() = AndroidSchedulers.mainThread()
}