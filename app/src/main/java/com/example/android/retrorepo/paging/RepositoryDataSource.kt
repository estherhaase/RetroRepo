package com.example.android.retrorepo.paging

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import com.example.android.retrorepo.enums.State
import com.example.android.retrorepo.remote.NetworkService
import com.example.android.retrorepo.remote.data.Item
import com.example.android.retrorepo.remote.data.Repository
import com.example.android.retrorepo.tools.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class RepositoryDataSource(private val networkService: NetworkService, private val keyword: String) :
    PageKeyedDataSource<Int, Item>() {


    var loadingState = MutableLiveData<State>()

    override fun loadInitial(
        params: PageKeyedDataSource.LoadInitialParams<Int>,
        callback: PageKeyedDataSource.LoadInitialCallback<Int, Item>
    ) {
        loadingState.postValue(State.LOADING)
        val call = networkService.getRepositories(keyword, 1)
        call.enqueue(object : Callback<Repository> {
            override fun onResponse(call: Call<Repository>, response: Response<Repository>) {
                if (response.code() == Constants.RESPONSE_OK) {
                    callback.onResult(response.body()!!.items, null, 2)
                    Timber.i("total count: %d", response.body()!!.totalCount)
                    loadingState.postValue(State.FINISHED)
                }
            }

            override fun onFailure(call: Call<Repository>, t: Throwable) {
                loadingState.postValue(State.ERROR)
            }
        })
    }

    override fun loadBefore(
        params: PageKeyedDataSource.LoadParams<Int>,
        callback: PageKeyedDataSource.LoadCallback<Int, Item>
    ) {

    }

    override fun loadAfter(
        params: PageKeyedDataSource.LoadParams<Int>,
        callback: PageKeyedDataSource.LoadCallback<Int, Item>
    ) {
        loadingState.postValue(State.LOADING)
        val call = networkService.getRepositories(keyword, params.key)
        call.enqueue(object : Callback<Repository> {
            override fun onResponse(call: Call<Repository>, response: Response<Repository>) {
                if (response.code() == Constants.RESPONSE_OK) {
                    val nextKey = params.key + 1
                    Timber.i("next key: %d", nextKey)

                    callback.onResult(response.body()!!.items, params.key + 1)
                }
            }

            override fun onFailure(call: Call<Repository>, t: Throwable) {
                loadingState.postValue(State.ERROR)
            }

        })

    }
}
