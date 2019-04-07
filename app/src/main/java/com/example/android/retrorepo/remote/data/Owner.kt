package com.example.android.retrorepo.remote.data

import com.google.gson.annotations.SerializedName

data class Owner(
    val login: String,

    @SerializedName("html_url")
    val url: String
)

