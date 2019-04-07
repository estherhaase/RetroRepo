package com.example.android.retrorepo.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import com.example.android.retrorepo.R
import com.example.android.retrorepo.ViewModelFactory
import com.example.android.retrorepo.enums.State
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item_loading.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var repositoryListAdapter: RepositoryListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = obtainViewModel(this)

        setupRecycler()

        //----------------- Listener

        buttonSearch.setOnClickListener { v ->
            viewModel.getData(editTextSearch.text.toString())

        }

        //----------------- Observer

        viewModel.repositoriesMediator.observe(this, Observer {
            repositoryListAdapter.submitList(it)
        })

        viewModel.state.observe(this, Observer {
            progressBar?.visibility =
                if (viewModel.isItemListEmpty() && it == State.LOADING) View.VISIBLE else View.GONE
            if (!viewModel.isItemListEmpty()) {
                repositoryListAdapter.setState(State.FINISHED)
            }
        })
    }


    private fun setupRecycler() {
        repositoryListAdapter = RepositoryListAdapter()
        recycler_repos.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        recycler_repos.adapter = repositoryListAdapter
    }

    fun obtainViewModel(activity: FragmentActivity): MainViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(MainViewModel::class.java)
    }


}
