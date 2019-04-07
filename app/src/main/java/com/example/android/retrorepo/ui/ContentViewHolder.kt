package com.example.android.retrorepo.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.retrorepo.R
import com.example.android.retrorepo.remote.data.Item
import kotlinx.android.synthetic.main.list_item_content.view.*

class ContentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(item: Item?) {
        if (item != null) {
            itemView.textName.text = item.name
            itemView.textLanguage.text = item.language
        }
    }

    companion object {
        fun create(parent: ViewGroup): ContentViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_content, parent, false)
            return ContentViewHolder(view)
        }
    }
}
