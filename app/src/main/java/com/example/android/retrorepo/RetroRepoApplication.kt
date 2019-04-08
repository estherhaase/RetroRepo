package com.example.android.retrorepo

import android.app.Application
import com.example.android.retrorepo.remote.LocalNetworkService
import com.example.android.retrorepo.remote.NetworkService
import com.example.android.retrorepo.storage.AppDatabase
import com.example.android.retrorepo.storage.DataRepository
import com.example.android.retrorepo.storage.LocalDataRepository
import timber.log.Timber

class RetroRepoApplication : Application() {

    private var appExecutors: AppExecutors

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    init {
        appExecutors = AppExecutors()
    }

    fun getNetworkService(): NetworkService {
        return LocalNetworkService.sInstance
    }

    fun getAppExecutors(): AppExecutors {
        return appExecutors
    }

    fun getAppDatabase(): AppDatabase {
        return AppDatabase.invoke(this, getAppExecutors())
    }

    fun getDataRepository(): DataRepository? {
        return LocalDataRepository.getInstance(this, getAppDatabase(), getAppExecutors())
    }
}
