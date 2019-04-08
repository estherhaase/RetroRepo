package com.example.android.retrorepo.storage

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.android.retrorepo.AppExecutors
import com.example.android.retrorepo.storage.dao.SearchTermDao
import com.example.android.retrorepo.storage.entities.SearchTermEntity

@Database(entities = [SearchTermEntity::class], version = 1)

abstract class AppDatabase : RoomDatabase() {

    abstract fun searchTermDao(): SearchTermDao

    companion object {
        @Volatile
        private var sInstance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context, appExecutors: AppExecutors) = sInstance ?: synchronized(LOCK) {
            sInstance ?: createDatabase(context, appExecutors).also { sInstance = it }
        }

        private fun createDatabase(context: Context, appExecutors: AppExecutors) = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "searchTerm.db"
        )
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    appExecutors.diskIO().execute { ->

                        val searchTermList = ArrayList<SearchTermEntity>()
                        searchTermList.add(
                            SearchTermEntity(
                                null,
                                "Java"
                            )
                        )
                        searchTermList.add(
                            SearchTermEntity(
                                null,
                                "Assembly"
                            )
                        )
                        searchTermList.add(
                            SearchTermEntity(
                                null,
                                "Agora"
                            )
                        )
                        searchTermList.add(
                            SearchTermEntity(
                                null,
                                "AngelScript"
                            )
                        )
                        searchTermList.add(
                            SearchTermEntity(
                                null,
                                "BASIC"
                            )
                        )
                        searchTermList.add(
                            SearchTermEntity(
                                null,
                                "Bubblesort"
                            )
                        )
                        searchTermList.add(SearchTermEntity(null, "C"))
                        searchTermList.add(SearchTermEntity(null, "C++"))
                        searchTermList.add(SearchTermEntity(null, "C#"))
                        searchTermList.add(
                            SearchTermEntity(
                                null,
                                "Darwin"
                            )
                        )
                        searchTermList.add(
                            SearchTermEntity(
                                null,
                                "Erlang"
                            )
                        )
                        searchTermList.add(
                            SearchTermEntity(
                                null,
                                "Euler"
                            )
                        )
                        searchTermList.add(
                            SearchTermEntity(
                                null,
                                "Fortran"
                            )
                        )
                        searchTermList.add(
                            SearchTermEntity(
                                null,
                                "Google"
                            )
                        )
                        searchTermList.add(SearchTermEntity(null, "Go"))
                        searchTermList.add(SearchTermEntity(null, "God"))
                        searchTermList.add(SearchTermEntity(null, "PHP"))
                        searchTermList.add(
                            SearchTermEntity(
                                null,
                                "JavaScript"
                            )
                        )
                        searchTermList.add(
                            SearchTermEntity(
                                null,
                                "React"
                            )
                        )
                        searchTermList.add(
                            SearchTermEntity(
                                null,
                                "Tetris"
                            )
                        )
                        searchTermList.add(
                            SearchTermEntity(
                                null,
                                "Haskell"
                            )
                        )
                        searchTermList.add(
                            SearchTermEntity(
                                null,
                                "JSON"
                            )
                        )
                        searchTermList.add(
                            SearchTermEntity(
                                null,
                                "Retrofit"
                            )
                        )
                        searchTermList.add(
                            SearchTermEntity(
                                null,
                                "Jake Wharton"
                            )
                        )
                        searchTermList.add(
                            SearchTermEntity(
                                null,
                                "Cobol"
                            )
                        )
                        searchTermList.add(
                            SearchTermEntity(
                                null,
                                "Kotlin"
                            )
                        )
                        searchTermList.add(
                            SearchTermEntity(
                                null,
                                "Pacman"
                            )
                        )
                        searchTermList.add(
                            SearchTermEntity(
                                null,
                                "Python"
                            )
                        )
                        searchTermList.add(
                            SearchTermEntity(
                                null,
                                "Ruby"
                            )
                        )
                        sInstance!!.searchTermDao().insertAll(searchTermList)

                    }
                }
            }
            )
            .build()
    }

}