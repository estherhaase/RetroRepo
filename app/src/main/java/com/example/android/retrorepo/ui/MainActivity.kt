package com.example.android.retrorepo.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.inputmethod.InputMethodManager
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
        setTheme(R.style.AppTheme_NoActionBar)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = obtainViewModel(this)

        setupRecycler()
        setupToolbar()

        //----------------- Listener

        buttonSearch.setOnClickListener { v ->
            viewModel.getData(editTextSearch.text.toString())
            editTextSearch.closeSoftKeyboard()

        }

        editTextSearch.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) buttonSearch.visibility = View.VISIBLE
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

    fun View.closeSoftKeyboard() {
        val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        val actionbar: ActionBar? = supportActionBar

        actionbar?.title = getString(R.string.search)
    }

}
