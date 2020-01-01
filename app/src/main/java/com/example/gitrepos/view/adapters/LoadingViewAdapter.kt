package com.example.gitrepos.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gitrepos.R

class LoadingViewAdapter : RecyclerView.Adapter<LoadingViewAdapter.LoadingViewHolder>() {


    class LoadingViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoadingViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.loading_item, parent, false)
        return LoadingViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return 20
    }

    override fun onBindViewHolder(holder: LoadingViewHolder, position: Int) {
    }
}