package com.example.gitrepos.dagger

import com.example.data.dagger.RepositoryModule
import com.example.data.network.dagger.RetrofitGitService
import com.example.gitrepos.GitApp
import com.example.gitrepos.view.dagger.BindingModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton


@Singleton
@Component(
    modules = [AndroidInjectionModule::class, BindingModule::class, RetrofitGitService::class, SchedulerModule::class, UsecaseModule::class, RepositoryModule::class]

)
interface ApplicationComponent : AndroidInjector<GitApp> {

}