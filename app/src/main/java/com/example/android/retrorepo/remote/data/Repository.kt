package com.example.android.retrorepo.remote.data

import com.google.gson.annotations.SerializedName

data class Repository(

    @SerializedName("total_count")
    private val totalCount: Int,

    @SerializedName("incomplete_results")
    @Transient
    internal var incompleteResults: Boolean,

    private val items: List<Item>
)
