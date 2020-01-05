package com.example.gitrepos.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.usecases.FetchRepositoryUsecase
import com.example.gitrepos.models.DomainViewMapper
import com.example.gitrepos.models.Repository
import io.reactivex.disposables.Disposable
import java.util.*
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
        if (repositoryListLiveData.value == null) {
            showLoading()
            fetchRepositoryUsecaseDisposable = fetchRepositoryUsecase.fetchGitRepositories()
                .map { DomainViewMapper().mapRepositories(it) }
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

    fun sortByName() {
        val data = repositoryListLiveData.value
        data?.run {
            Collections.sort(data, NameComparator())
            repositoryListLiveData.postValue(data)
        }

    }

    fun sortByStars() {
        val data = repositoryListLiveData.value
        data?.run {
            Collections.sort(data, StarComparator())
            repositoryListLiveData.postValue(data)
        }
    }

    class NameComparator : Comparator<Repository> {

        override fun compare(o1: Repository?, o2: Repository?): Int {

            return o1?.name?.compareTo(o2?.name ?: "") ?: 0
        }
    }

    class StarComparator : Comparator<Repository> {

        override fun compare(o1: Repository?, o2: Repository?): Int {

            return  o1?.stars?.compareTo(o2?.stars ?: 0) ?: 0
        }
    }
}