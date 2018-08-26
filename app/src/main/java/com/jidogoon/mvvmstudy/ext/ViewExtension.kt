package com.jidogoon.mvvmstudy.ext

import android.view.View
import android.widget.ImageView
import com.jidogoon.mvvmstudy.image.IImageRequester

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun ImageView.setUrl(requester: IImageRequester, url: String?) {
    requester.setUrl(this, url)
}