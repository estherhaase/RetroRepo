package com.example.android.retrorepo.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.android.retrorepo.R

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }


}
