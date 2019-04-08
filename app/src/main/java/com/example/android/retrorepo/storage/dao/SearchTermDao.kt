package com.example.android.retrorepo.storage.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.example.android.retrorepo.storage.entities.SearchTermEntity

@Dao
interface SearchTermDao {

    @Query("SELECT * FROM searchTerms")
    fun getAllSync(): List<SearchTermEntity>

    @Query("SELECT * FROM searchTerms")
    fun getAll(): LiveData<List<SearchTermEntity>>

    @Query("SELECT * FROM searchTerms WHERE term_name LIKE :name")
    fun findByTitle(name: String): SearchTermEntity

    @Insert
    fun insertAll(searchTerm: List<SearchTermEntity>)

    @Delete
    fun delete(termEntity: SearchTermEntity)

    @Update
    fun updateSearchTerm(searchTerms: SearchTermEntity)
}