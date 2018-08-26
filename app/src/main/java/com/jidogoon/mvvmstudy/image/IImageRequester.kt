package com.jidogoon.mvvmstudy.image

import android.widget.ImageView

interface IImageRequester {
    fun setUrl(imageView: ImageView, url: String?)
}