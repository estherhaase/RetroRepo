package com.example.android.retrorepo

import android.app.Application
import com.example.android.retrorepo.remote.LocalNetworkService
import com.example.android.retrorepo.remote.NetworkService
import timber.log.Timber

class RetroRepoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    public fun getNetworkService(): NetworkService {
        return LocalNetworkService.sInstance
    }
}
