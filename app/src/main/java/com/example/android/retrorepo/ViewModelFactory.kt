package com.example.android.retrorepo

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.android.retrorepo.remote.NetworkService
import com.example.android.retrorepo.ui.MainViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory private constructor(
    private val application: Application,
    private val networkService: NetworkService
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(networkService) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel Class: " + modelClass.name)
        }
    }

    companion object {

        @Volatile
        private var sInstance: ViewModelFactory? = null

        fun getInstance(application: Application): ViewModelFactory? {
            if (sInstance == null) {
                synchronized(ViewModelFactory::class.java) {
                    if (sInstance == null) {
                        if (application is RetroRepoApplication) {
                            sInstance = ViewModelFactory(application, application.getNetworkService())

                        } else {
                            throw RuntimeException("Application class must be of type RetroRepoApplication")

                        }
                    }
                }
            }
            return sInstance
        }
    }
}
