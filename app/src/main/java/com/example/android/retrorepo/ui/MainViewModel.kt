package com.example.android.retrorepo.ui

import android.arch.lifecycle.LiveData
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

    var repositories: LiveData<PagedList<Item>>
    private val repositoryDataSourceFactory: RepositoryDataSourceFactory


    /*  private lateinit var repositoryDataSourceFactory: RepositoryDataSourceFactory

     init {
         repositories = MutableLiveData<PagedList<Item>>()
     }

    fun initDataSourceFactory(keyword:String) {
         repositoryDataSourceFactory = RepositoryDataSourceFactory(networkService, keyword)
         val config = PagedList.Config.Builder()
             .setPageSize(30)
             .setEnablePlaceholders(true)
             .setInitialLoadSizeHint(10)
             .build()
         repositories = LivePagedListBuilder<Int, Item>(repositoryDataSourceFactory, config).build()
     }*/

    init {

        repositoryDataSourceFactory = RepositoryDataSourceFactory(networkService, "tetris")
        val config = PagedList.Config.Builder()
            .setPageSize(30)
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(10)
            .build()
        repositories = LivePagedListBuilder<Int, Item>(repositoryDataSourceFactory, config).build()
    }

    fun getState(): LiveData<State> = Transformations
        .switchMap<RepositoryDataSource, State>(
            repositoryDataSourceFactory.repositoryDataSourceLiveData,
            RepositoryDataSource::loadingState
        )

}

