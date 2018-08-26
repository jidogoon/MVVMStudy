package com.jidogoon.mvvmstudy.main

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jidogoon.mvvmstudy.image.IImageRequester

class MainAdapter(
        private val viewModel: MainViewModel,
        private val imageRequester: IImageRequester): RecyclerView.Adapter<MainItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainItemViewHolder = MainItemViewHolder(parent, imageRequester)

    override fun getItemCount(): Int = viewModel.getPhotosCount()

    override fun onBindViewHolder(holder: MainItemViewHolder, position: Int) {
        holder.bind(viewModel.getPhotoItem(position))
    }
}