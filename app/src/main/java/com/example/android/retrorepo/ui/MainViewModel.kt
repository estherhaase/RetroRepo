package com.example.android.retrorepo.ui

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.android.retrorepo.remote.NetworkService
import com.example.android.retrorepo.remote.data.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val networkService: NetworkService) : ViewModel() {


    var repositories: MutableLiveData<Repository> = MutableLiveData()

    fun loadRepositories(keyword: String, page: Int) {
        val call = networkService.getRepositories(keyword, page)
        call.enqueue(object : Callback<Repository> {
            override fun onResponse(call: Call<Repository>, response: Response<Repository>) {
                repositories.postValue(response.body())
            }

            override fun onFailure(call: Call<Repository>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

    }
}

