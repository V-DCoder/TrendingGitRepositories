package com.example.gitrepos.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.entity.Repository
import com.example.domain.usecases.FetchRepositoryUsecase
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class GitRepositoryViewModel @Inject constructor(private val fetchRepositoryUsecase: FetchRepositoryUsecase) :
    ViewModel() {


    private var fetchRepositoryUsecaseDisposable: Disposable? = null
    private val progressbarVisibilityLiveData = MutableLiveData<Boolean>()
    private val errorViewVisibilityLiveData = MutableLiveData<Boolean>()
    private val repositoryListLiveData = MutableLiveData<List<Repository>>()

    fun getProgressBarVisibilityLiveData(): LiveData<Boolean> = progressbarVisibilityLiveData
    fun getErrorViewVisibilityLiveData(): LiveData<Boolean> = errorViewVisibilityLiveData
    fun getRepositoryListLiveData(): LiveData<List<Repository>> = repositoryListLiveData

    fun fetchRepositories() {
        if(repositoryListLiveData.value==null) {
            showLoading()
            fetchRepositoryUsecaseDisposable = fetchRepositoryUsecase.fetchGitRepositories()
                .subscribe({ onSuccess(it) }, { onError(it) })
        }
    }

    private fun showLoading() {
        progressbarVisibilityLiveData.postValue(true)
    }

    private fun hideLoading() {
        progressbarVisibilityLiveData.postValue(false)
    }

    private fun showError() {
        errorViewVisibilityLiveData.postValue(true)
    }

    private fun hideError() {
        errorViewVisibilityLiveData.postValue(false)
    }


    private fun onError(throwable: Throwable?) {
        throwable?.printStackTrace()
        hideLoading()
        showError()
    }

    private fun onSuccess(list: List<Repository>?) {
        hideLoading()
        if (list == null) {
            showError()
        } else {
            hideError()
            repositoryListLiveData.postValue(list)

        }


    }

    override fun onCleared() {
        super.onCleared()
        fetchRepositoryUsecaseDisposable?.dispose()
    }
}