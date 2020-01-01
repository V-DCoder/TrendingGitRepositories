package com.example.gitrepos.dagger


import com.example.gitrepos.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Module to declare UI components that have injectable members
 */
@Module
abstract class BindingModule {
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

}