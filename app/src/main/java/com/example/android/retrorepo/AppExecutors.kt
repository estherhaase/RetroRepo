package com.example.android.retrorepo

import java.util.concurrent.Executor
import java.util.concurrent.Executors

class AppExecutors @JvmOverloads constructor(private val diskIO: Executor = Executors.newSingleThreadExecutor()) {

    fun diskIO(): Executor {
        return diskIO
    }
}
