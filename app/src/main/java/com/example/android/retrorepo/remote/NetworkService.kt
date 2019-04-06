package com.example.android.retrorepo.remote

import com.example.android.retrorepo.remote.data.Repository
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET("/search/repositories")
    fun getRepositories(@Query("q") keyword: String, @Query("page") page: Int): Call<Repository>

    //fun getRepositoriesByLanguage(@Query("q")keyword: String, @Query("") language: String): LiveData<List<Repository>>?

}
