package com.example.gitrepos.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.domain.entity.Repository
import com.example.domain.usecases.FetchRepositoryUsecase
import com.example.gitrepos.R
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }


    @Inject
    lateinit var fetchRepositoryUsecase: FetchRepositoryUsecase

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchRepositoryUsecase.fetchGitRepositories().subscribe({ onSuccrss(it) }, { onError(it) })
    }

    private fun onError(throwable: Throwable?) {
        Log.w("Failuar", throwable?.message ?: "message is empty")
    }

    private fun onSuccrss(list: List<Repository>?) {
        Log.w("Success", "size : " + list?.size)
    }
}
