package com.example.android.retrorepo.remote

import android.arch.lifecycle.LiveData
import com.example.android.retrorepo.remote.data.Repository

class LocalNetworkService private constructor() : NetworkService {


    override fun getRepositories(keyword: String): LiveData<List<Repository>>? {
        return null
    }

    override fun getRepositoriesByLanguage(keyword: String, language: String): LiveData<List<Repository>>? {
        return null
    }

    companion object {

        val sInstance = LocalNetworkService()
        /*   @Volatile
           private var sInstance: LocalNetworkService? = null

           val instance: LocalNetworkService?
               get() {
                   if (sInstance == null) {
                       synchronized(LocalNetworkService::class.java) {
                           if (sInstance == null) {
                               sInstance = LocalNetworkService()
                           }
                       }
                   }
                   return sInstance
               }*/
    }
}
