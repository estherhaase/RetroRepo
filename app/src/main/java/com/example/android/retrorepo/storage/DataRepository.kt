package com.example.android.retrorepo.storage

import android.arch.lifecycle.LiveData
import com.example.android.retrorepo.storage.entities.SearchTermEntity

interface DataRepository {
    fun getSearchTerms(): LiveData<List<SearchTermEntity>>

}
