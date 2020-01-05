package com.example.gitrepos.view


import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.gitrepos.R
import com.example.gitrepos.models.Repository
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
import kotlinx.android.synthetic.main.error_view.*
import kotlinx.android.synthetic.main.loading_view.*
import kotlinx.android.synthetic.main.more_option_popup.view.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasAndroidInjector {

    private lateinit var popupMenu: PopupWindow
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
        initView()
        initActionBar()

    }

    private fun initActionBar() {
        setSupportActionBar(toobar)
    }

    private fun initView() {
        rcvLoading.adapter = LoadingViewAdapter()
        repositoryListAdapter = RepositoriesListAdapter()
        rcvRepositoryList.adapter = repositoryListAdapter
        val divider = DividerItemDecoration(this, LinearLayout.VERTICAL)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            divider.setDrawable(resources.getDrawable(R.drawable.divider_drawable,theme))
        } else {
            divider.setDrawable(resources.getDrawable(R.drawable.divider_drawable))
        }

        rcvRepositoryList.addItemDecoration(divider)
        popupMenu = initPopupWindow()
        retryButton.setOnClickListener { gitRepositoryViewModel.fetchRepositories() }
        moreOptions.setOnClickListener { showMoreOptionPopup() }


    }

    private fun showMoreOptionPopup() {
        if (popupMenu.isShowing) {
            popupMenu.dismiss()
        } else {
            popupMenu.showAsDropDown(moreOptions)
        }
    }

    private fun initPopupWindow(): PopupWindow {
        val popupMenu = PopupWindow(this)
        val view = LayoutInflater.from(this).inflate(R.layout.more_option_popup, null)
        view.sortByName.setOnClickListener { sortByName() }
        view.sortByStars.setOnClickListener { sortByStar() }
        popupMenu.contentView = view
        return popupMenu
    }

    private fun sortByName() {
        popupMenu.dismiss()
        gitRepositoryViewModel.sortByName()
    }

    private fun sortByStar() {
        gitRepositoryViewModel.sortByStars()
        popupMenu.dismiss()
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
