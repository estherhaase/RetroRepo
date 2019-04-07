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
import com.example.android.retrorepo.tools.Constants

class MainViewModel(private val networkService: NetworkService) : ViewModel() {

    private lateinit var repositoryDataSourceFactory: RepositoryDataSourceFactory
    var repositoriesMediator = MediatorLiveData<PagedList<Item>>()
    var state = MediatorLiveData<State>()


    fun getData(keyword: String) {
        val data = initDataSourceFactory(keyword)
        repositoriesMediator.addSource(data) {

            repositoriesMediator.postValue(it)

            repositoriesMediator.removeSource(data)
        }
        getState()
    }

    fun getState() {
        val data = setState()
        state.addSource(data) {
            state.postValue(it)
            state.removeSource(data)
        }
    }


    fun initDataSourceFactory(keyword: String): LiveData<PagedList<Item>> {
        repositoryDataSourceFactory = RepositoryDataSourceFactory(networkService, keyword)
        val config = PagedList.Config.Builder()
            .setPageSize(Constants.PAGE_SIZE)
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(Constants.INITIAL_LOAD_HINT)
            .build()

        return LivePagedListBuilder<Int, Item>(repositoryDataSourceFactory, config).build()
    }

    fun setState(): LiveData<State> = Transformations
        .switchMap<RepositoryDataSource, State>(
            repositoryDataSourceFactory.repositoryDataSourceLiveData,
            RepositoryDataSource::loadingState
        )

    fun isItemListEmpty(): Boolean {
        return repositoriesMediator.value?.isEmpty() ?: true
    }

}

