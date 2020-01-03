package com.example.gitrepos.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.domain.entity.Repository
import com.example.gitrepos.R
import com.example.gitrepos.view.adapters.LoadingViewAdapter
import com.example.gitrepos.view.adapters.RepositoriesListAdapter
import com.example.gitrepos.viewmodels.GitRepositoryViewModel
import com.example.gitrepos.viewmodels.ViewModelFactory
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.data_view.*
import kotlinx.android.synthetic.main.loading_view.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasAndroidInjector {

    private lateinit var repositoryListAdapter: RepositoriesListAdapter
    private lateinit var gitRepositoryViewModel: GitRepositoryViewModel
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        gitRepositoryViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(GitRepositoryViewModel::class.java)
        init()
        gitRepositoryViewModel.fetchRepositories()
    }

    private fun init() {
        initObservers()
        inView()
        initActionBar()

    }

    private fun initActionBar() {
        setSupportActionBar(toobar)
    }

    private fun inView() {
        rcvLoading.adapter = LoadingViewAdapter()
        repositoryListAdapter = RepositoriesListAdapter()
        rcvRepositoryList.adapter = repositoryListAdapter


    }

    private fun initObservers() {
        gitRepositoryViewModel.getErrorViewVisibilityLiveData()
            .observe(this, Observer { onErrorViewVisibilityChange(it) })
        gitRepositoryViewModel.getProgressBarVisibilityLiveData()
            .observe(this, Observer { onProgressBarVisibilityChange(it) })
        gitRepositoryViewModel.getRepositoryListLiveData()
            .observe(this, Observer { onRepositoryListReceived(it) })
    }

    private fun onRepositoryListReceived(repositoryList: List<Repository>) {
        dataView.setVisibility(true)
        repositoryListAdapter.setRepoList(repositoryList)
    }

    private fun onProgressBarVisibilityChange(shouldShow: Boolean?) {
        loadingView.setVisibility(shouldShow)
        dataView.setVisibility(false)
    }

    private fun View.setVisibility(shouldShow: Boolean?) {
        visibility = if (true == shouldShow) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    private fun onErrorViewVisibilityChange(shouldShow: Boolean?) {
        errorView.setVisibility(shouldShow)
        dataView.setVisibility(false)
    }


}
