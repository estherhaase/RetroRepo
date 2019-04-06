package com.example.android.retrorepo.paging

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.example.android.retrorepo.remote.NetworkService
import com.example.android.retrorepo.remote.data.Item

class RepositoryDataSourceFactory(private val networkService: NetworkService, private val keyWord: String) :
    DataSource.Factory<Int, Item>() {
    val repositoryDataSourceLiveData = MutableLiveData<RepositoryDataSource>()

    override fun create(): DataSource<Int, Item> {
        val repositoryDataSource = RepositoryDataSource(networkService, keyWord)
        repositoryDataSourceLiveData.postValue(repositoryDataSource)
        return repositoryDataSource
    }
}