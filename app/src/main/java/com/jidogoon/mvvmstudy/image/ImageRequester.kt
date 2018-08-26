package com.jidogoon.mvvmstudy.image

import android.widget.ImageView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade

class ImageRequester(private val requestManager: RequestManager): IImageRequester {
    override fun setUrl(imageView: ImageView, url: String?) {
        requestManager.load(url).transition(withCrossFade()).into(imageView)
    }
}