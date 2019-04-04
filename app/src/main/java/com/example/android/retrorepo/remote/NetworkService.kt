package com.example.android.retrorepo.remote

import android.arch.lifecycle.LiveData
import com.example.android.retrorepo.remote.data.Repository

interface NetworkService {

    fun getRepositories(keyword: String): LiveData<List<Repository>>?

    fun getRepositoriesByLanguage(keyword: String, language: String): LiveData<List<Repository>>?
}
