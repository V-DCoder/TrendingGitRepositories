package com.example.gitrepos.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gitrepos.R
import com.example.gitrepos.models.Repository
import com.example.gitrepos.view.utils.CirculImageView
import com.example.gitrepos.view.utils.CircularImageTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.repositories_list_item.view.*

class RepositoriesListAdapter :
    RecyclerView.Adapter<RepositoriesListAdapter.RepositoriesListViewHolder>() {

    var repositoryList: List<Repository>? = null

    fun setRepoList(repositoryList: List<Repository>?) {
        this.repositoryList = repositoryList
        notifyDataSetChanged()
    }

    inner class RepositoriesListViewHolder(val view: View) : RecyclerView.ViewHolder(view) {


        val description: TextView = this.view.description
        val author: TextView = this.view.author
        val name: TextView = this.view.name
        val fork: TextView = this.view.fork
        val language: TextView = this.view.language
        val stars: TextView = this.view.stars
        val avatar: ImageView = this.view.avatar
        val languageColor: CirculImageView = this.view.languageColour
        val detailsLayout: LinearLayout = this.view.detailsLayout

        init {
            view.setOnClickListener { onItemClick() }
        }

        private fun onItemClick() {
            repositoryList?.forEach { it.isSelected = false }
            repositoryList?.get(adapterPosition)?.isSelected = true
            notifyDataSetChanged()
        }
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
                .transform(CircularImageTransform()).into(holder.avatar)
            languageColor.setCustomColor(repo?.languageColor)
            if (repo?.isSelected == true) {
                detailsLayout.visibility = VISIBLE
                description.visibility = VISIBLE
            } else {
                detailsLayout.visibility = GONE
                description.visibility = GONE
            }

        }

    }
}