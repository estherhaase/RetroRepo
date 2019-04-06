package com.example.android.retrorepo.remote.data

import com.google.gson.annotations.SerializedName

data class Item(

    private val id: Int,

    @SerializedName("node_id")
    @Transient
    internal var nodeId: String,

    private val name: String,

    @Transient
    internal var owner: Owner,

    @SerializedName("private")
    @Transient
    internal var isPrivate: Boolean,

    @SerializedName("html_url")
    private val repoUrl: String,

    private val description: String,

    @Transient
    internal var fork: Boolean,

    @Transient
    internal var url: String,

    @SerializedName("created_at")
    @Transient
    internal var createdAt: String,

    @SerializedName("updated_at")
    @Transient
    internal var updateddAt: String,

    @SerializedName("pushed_at")
    @Transient
    internal var pushedAt: String,

    @Transient
    internal var homepage: String,

    @Transient
    internal var size: Int,

    @SerializedName("stargazers_count")
    @Transient
    internal var startgazers: Int,

    @SerializedName("watchers_count")
    @Transient
    internal var watchers: Int,

    private val language: String,

    @SerializedName("forks_count")
    @Transient
    internal var forks: Int,

    @SerializedName("open_issues_count")
    @Transient
    internal var openIssues: Int,

    @SerializedName("master_branch")
    @Transient
    internal var masterBranch: String,

    @SerializedName("default_branch")
    @Transient
    internal var defaultBranch: String,

    private val score: Double

)
