package com.example.android.retrorepo.remote.data

import com.google.gson.annotations.SerializedName

data class Item(

 val id: Int,

 val name: String,

 val owner: Owner,

 @SerializedName("html_url")
 val repoUrl: String,

 val description: String,

 val language: String,

 val score: Double

)
