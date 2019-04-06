package com.example.android.retrorepo.ui

import android.arch.lifecycle.ViewModel
import com.example.android.retrorepo.remote.NetworkService

class MainViewModel(private val networkService: NetworkService) : ViewModel() {

    
    /*  var repositories: MutableLiveData<Repository> = MutableLiveData()


      fun loadRepositories(keyword: String, page: Int) {
          val call = networkService.getRepositories(keyword, page)
          call.enqueue(object : Callback<Repository> {
              override fun onResponse(call: Call<Repository>, response: Response<Repository>) {
                  repositories.postValue(response.body())
              }

              override fun onFailure(call: Call<Repository>, t: Throwable) {
              }
          })

      }*/
}

