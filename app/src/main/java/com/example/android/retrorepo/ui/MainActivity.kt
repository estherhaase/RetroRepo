package com.example.android.retrorepo.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v4.app.FragmentActivity
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import com.example.android.retrorepo.R
import com.example.android.retrorepo.ViewModelFactory
import com.example.android.retrorepo.enums.State
import com.example.android.retrorepo.remote.data.Item
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_repository_detail.*
import kotlinx.android.synthetic.main.layout_repository_detail.view.*
import kotlinx.android.synthetic.main.list_item_loading.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var repositoryListAdapter: RepositoryListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme_NoActionBar)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = obtainViewModel(this)
        var bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)

        setupRecycler()
        setupToolbar()

        //----------------- Listener

        bottomSheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(view: View, state: Int) {
                when (state) {
                    BottomSheetBehavior.STATE_COLLAPSED -> imageBottomSheet.setImageResource(R.drawable.ic_arrow_up)
                    BottomSheetBehavior.STATE_EXPANDED -> imageBottomSheet.setImageResource(R.drawable.ic_arrow_down)
                }
            }

            override fun onSlide(p0: View, p1: Float) {
            }
        })

        buttonSearch.setOnClickListener { v ->
            progressBarHome.visibility = View.VISIBLE
            viewModel.getData(editTextSearch.text.toString())
            editTextSearch.closeSoftKeyboard()

        }

        editTextSearch.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) buttonSearch.visibility = View.VISIBLE
        }

        textUrl.setOnClickListener { v ->
            val i = Intent(Intent.ACTION_VIEW, Uri.parse(v.textUrl.text.toString()))
            startActivity(i)
        }

        textUserUrl.setOnClickListener { v ->
            val i = Intent(Intent.ACTION_VIEW, Uri.parse(v.textUserUrl.text.toString()))
            startActivity(i)
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

        viewModel.searchTerms.observe(this, Observer {
            val list = ArrayList<String>()
            for (t in it!!.iterator()) {
                list.add(t.name)
            }
            val adapter = ArrayAdapter<String>(this, R.layout.list_item_dropdown, list)
            editTextSearch.setAdapter(adapter)

        })

        repositoryListAdapter.setOnItemClickListener(object : RepositoryListAdapter.ItemClickListener {
            override fun onClick(item: Item?) {
                if (item != null) {
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                    layoutDetail.visibility = View.VISIBLE
                    bottomSheet.textUserUrl.text = item.owner.url
                    bottomSheet.textUser.text = item.owner.login
                    bottomSheet.textCount.text = item.score.toString()
                    bottomSheet.textDescription.text = item.description
                    bottomSheet.textLanguageDetail.text = item.language
                    bottomSheet.textUrl.text = item.repoUrl
                    Picasso.get().load(item.owner.avatar).placeholder(R.drawable.ic_launcher_background)
                        .resize(160, 160).into(imageUser)

                }
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
