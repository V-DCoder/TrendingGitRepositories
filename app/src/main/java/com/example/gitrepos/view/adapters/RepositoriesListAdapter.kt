package com.example.gitrepos.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.Repository
import com.example.gitrepos.R
import com.example.gitrepos.view.utils.RoundCornersTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.repositories_list_item.view.*

class RepositoriesListAdapter() :
    RecyclerView.Adapter<RepositoriesListAdapter.RepositoriesListViewHolder>() {

    var repositoryList: List<Repository>? = null

    fun setRepoList(repositoryList: List<Repository>?) {
        this.repositoryList = repositoryList
        notifyDataSetChanged()
    }

    class RepositoriesListViewHolder(val view: View) : RecyclerView.ViewHolder(view) {


        val description = this.view.description
        val author = this.view.author
        val name = this.view.name
        val fork = this.view.fork
        val language = this.view.language
        val stars = this.view.stars
        val avatar = this.view.avatar
        val languageColor = this.view.languageColour

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoriesListViewHolder {

        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.repositories_list_item, parent, false)

        return RepositoriesListViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return repositoryList?.size ?: 0
    }

    override fun onBindViewHolder(holder: RepositoriesListViewHolder, position: Int) {
        val repo = repositoryList?.get(position)
        holder.apply {
            author.text = repo?.author
            name.text = repo?.name
            description.text = repo?.description
            stars.text = repo?.stars.toString()
            fork.text = repo?.forks.toString()
            language.text = repo?.language
            Picasso.get().load(repo?.avatar)
                .transform(RoundCornersTransform()).into(holder.avatar)
            languageColor.setCustomColor(repo?.languageColor)

        }

    }
}