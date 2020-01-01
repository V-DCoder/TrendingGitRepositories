package com.example.gitrepos.dagger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gitrepos.viewmodels.GitRepositoryViewModel
import com.example.gitrepos.viewmodels.ViewModelFactory
import com.example.gitrepos.viewmodels.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(GitRepositoryViewModel::class)
    abstract fun getViewModel(viewmodel: GitRepositoryViewModel): ViewModel

    @Binds
    abstract fun provideViewModelFactore(factory: ViewModelFactory): ViewModelProvider.Factory

}