package com.example.android.retrorepo.ui

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.android.retrorepo.enums.State
import com.example.android.retrorepo.remote.data.Item
import com.example.android.retrorepo.tools.Constants

class RepositoryListAdapter() : PagedListAdapter<Item, RecyclerView.ViewHolder>(ItemsDifferCallback) {

    private var state = State.LOADING

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == Constants.CONTENT_VIEW_TYPE) ContentViewHolder.create(parent) else LoadingViewHolder.create(
            parent
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == Constants.CONTENT_VIEW_TYPE)
            (holder as ContentViewHolder).bind(getItem(position))
        else (holder as LoadingViewHolder).bind(state)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < super.getItemCount()) Constants.CONTENT_VIEW_TYPE else Constants.LOADING_VIEW_TYPE
    }


    companion object {
        val ItemsDifferCallback = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }
        }

    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }

    private fun isLoadingOrError(): Boolean {
        return super.getItemCount() != 0 && (state == State.LOADING || state == State.ERROR)
    }

    fun setState(state: State) {
        this.state = state
        notifyItemChanged(super.getItemCount())
    }
}