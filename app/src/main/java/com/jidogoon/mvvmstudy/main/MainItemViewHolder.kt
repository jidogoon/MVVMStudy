package com.jidogoon.mvvmstudy.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jidogoon.mvvmstudy.R
import com.jidogoon.mvvmstudy.data.Photo
import com.jidogoon.mvvmstudy.ext.setUrl
import com.jidogoon.mvvmstudy.image.IImageRequester
import kotlinx.android.synthetic.main.list_item_photo.view.*

class MainItemViewHolder(parent: ViewGroup,
                         private val imageRequester: IImageRequester): RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.list_item_photo, parent, false)) {

    fun bind(photo: Photo?) {
        with(itemView) {
            title.text = photo?.title
            photoThumb.setUrl(imageRequester, photo?.thumbnailUrl)
        }
    }
}