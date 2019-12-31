package com.example.gitrepos.view.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.entity.Repository
import com.example.domain.usecases.FetchRepositoryUsecase
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class GitRepositoryViewModel @Inject constructor(val fetchRepositoryUsecase: FetchRepositoryUsecase) :
    ViewModel() {


    private var fetchRepositoryUsecaseDisposable: Disposable? = null
    private val progressbarVisibility = MutableLiveData<Boolean>()
    private val errorViewVisibility = MutableLiveData<Boolean>()
    private val repositoryList = MutableLiveData<List<Repository>>()

    fun getRepositories() {
        fetchRepositoryUsecaseDisposable = fetchRepositoryUsecase.fetchGitRepositories()
            .subscribe({ onSuccess(it) }, { onError(it) })
    }

    private fun onError(throwable: Throwable?) {
        throwable?.printStackTrace()
        errorViewVisibility.postValue(true)
    }

    private fun onSuccess(list: List<Repository>?) {
        progressbarVisibility.postValue(false)
        list?.run {
            errorViewVisibility.postValue(false)
            repositoryList.postValue(this)
        }
    }

    override fun onCleared() {
        super.onCleared()
        fetchRepositoryUsecaseDisposable?.dispose()
    }
}