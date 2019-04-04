package com.example.android.retrorepo.ui

import android.arch.lifecycle.ViewModel
import com.example.android.retrorepo.remote.NetworkService

class MainViewModel(private val networkService: NetworkService) : ViewModel()
