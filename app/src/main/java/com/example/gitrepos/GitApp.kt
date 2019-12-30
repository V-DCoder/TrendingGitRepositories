package com.example.gitrepos

import android.app.Application
import com.example.gitrepos.dagger.DaggerApplicationComponent
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
        DaggerApplicationComponent.create().inject(this)
    }
}