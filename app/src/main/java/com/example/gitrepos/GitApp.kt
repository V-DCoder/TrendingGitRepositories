package com.example.gitrepos

import android.app.Application
import com.example.data.dagger.RepositoryModule
import com.example.gitrepos.dagger.DaggerApplicationComponent
import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class GitApp : Application(), HasAndroidInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector!!
    }

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent.builder().repositoryModule(RepositoryModule(this)).build().inject(this)
    }
}