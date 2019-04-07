package com.example.android.retrorepo.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.example.android.retrorepo.enums.State
import com.example.android.retrorepo.paging.RepositoryDataSource
import com.example.android.retrorepo.paging.RepositoryDataSourceFactory
import com.example.android.retrorepo.remote.NetworkService
import com.example.android.retrorepo.remote.data.Item

class MainViewModel(private val networkService: NetworkService) : ViewModel() {

    private lateinit var repositoryDataSourceFactory: RepositoryDataSourceFactory
    var repositoriesMediator = MediatorLiveData<PagedList<Item>>()


    fun getData(keyword: String) {
        val data = initDataSourceFactory(keyword)
        repositoriesMediator.addSource(data) {

            repositoriesMediator.postValue(it)

            repositoriesMediator.removeSource(data)
        }
    }


    fun initDataSourceFactory(keyword: String): LiveData<PagedList<Item>> {
        repositoryDataSourceFactory = RepositoryDataSourceFactory(networkService, keyword)
        val config = PagedList.Config.Builder()
            .setPageSize(30)
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(10)
            .build()
        return LivePagedListBuilder<Int, Item>(repositoryDataSourceFactory, config).build()
    }

    fun getState(): LiveData<State> = Transformations
        .switchMap<RepositoryDataSource, State>(
            repositoryDataSourceFactory.repositoryDataSourceLiveData,
            RepositoryDataSource::loadingState
        )

}

