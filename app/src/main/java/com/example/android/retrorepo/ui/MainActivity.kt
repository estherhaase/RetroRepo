package com.example.android.retrorepo.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.example.android.retrorepo.R
import com.example.android.retrorepo.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var repositoryListAdapter: RepositoryListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = obtainViewModel(this)
        // viewModel.loadRepositories("tetris", 1)

        /* viewModel.repositories.observe(this, Observer { repositories: Repository? ->

         })*/

        repositoryListAdapter = RepositoryListAdapter()

        recycler_repos.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        recycler_repos.adapter = repositoryListAdapter
        /* buttonSearch.setOnClickListener { v ->
             viewModel.initDataSourceFactory(editTextSearch.text.toString())
         }*/

        viewModel.repositories.observe(this, Observer {
            repositoryListAdapter.submitList(it)
        })


    }


    fun obtainViewModel(activity: FragmentActivity): MainViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(MainViewModel::class.java)
    }


}
