package com.example.android.retrorepo.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.example.android.retrorepo.R
import com.example.android.retrorepo.enums.State
import kotlinx.android.synthetic.main.list_item_loading.view.*

class LoadingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(loading: State) {
        itemView.progressBar.visibility = if (loading == State.LOADING) VISIBLE else INVISIBLE
    }

    companion object {
        fun create(parent: ViewGroup): LoadingViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_loading, parent, false)
            return LoadingViewHolder(view)
        }
    }
}