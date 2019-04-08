package com.example.android.retrorepo.storage

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import com.example.android.retrorepo.AppExecutors
import com.example.android.retrorepo.storage.dao.SearchTermDao
import com.example.android.retrorepo.storage.entities.SearchTermEntity

class LocalDataRepository(context: Context, private val executors: AppExecutors, dataBase: AppDatabase) :
    DataRepository {
    private val dataBase: AppDatabase = AppDatabase.invoke(context, executors)

    private val searchTermDao: SearchTermDao = dataBase.searchTermDao()

    companion object {

        @Volatile
        private var sInstance: LocalDataRepository? = null

        fun getInstance(context: Context, dataBase: AppDatabase, executors: AppExecutors): LocalDataRepository? {
            if (sInstance == null) {
                synchronized(LocalDataRepository::class.java) {
                    if (sInstance == null) {
                        sInstance = LocalDataRepository(context, executors, dataBase)
                    }
                }
            }
            return sInstance
        }
    }


    override fun getSearchTerms(): LiveData<List<SearchTermEntity>> {
        val result = MutableLiveData<List<SearchTermEntity>>()
        executors.diskIO().execute { ->
            val list = searchTermDao.getAllSync()
            result.postValue(list)
        }
        return result
    }
}
