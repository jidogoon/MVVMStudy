package com.jidogoon.mvvmstudy.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.bumptech.glide.Glide
import com.jidogoon.mvvmstudy.R
import com.jidogoon.mvvmstudy.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import com.jidogoon.mvvmstudy.main.MainViewModel.ViewStatus
import com.jidogoon.mvvmstudy.ext.hide
import com.jidogoon.mvvmstudy.ext.show
import com.jidogoon.mvvmstudy.image.ImageRequester
import com.jidogoon.mvvmstudy.repo.RemoteRepository

class MainActivity : AppCompatActivity() {
    private val viewModel by lazy {
        val repository = RemoteRepository()
        ViewModelProviders.of(this, ViewModelFactory(repository)).get(MainViewModel::class.java).apply {
            viewStatus.observe(this@MainActivity, statusObserver)
        }
    }

    private val statusObserver = Observer<MainViewModel.ViewStatus> { status ->
        when(status) {
            ViewStatus.LOADING -> loading.show()
            ViewStatus.DONE -> {
                loading.hide()
                mainRecyclerView.adapter?.notifyDataSetChanged()
            }
            ViewStatus.ERROR -> Toast.makeText(MainActivity@this, R.string.error, Toast.LENGTH_SHORT).show()
            else -> {}
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        mainRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 4, OrientationHelper.VERTICAL, false)
            adapter = MainAdapter(viewModel, ImageRequester(Glide.with(MainActivity@ this)))
        }
    }
}
