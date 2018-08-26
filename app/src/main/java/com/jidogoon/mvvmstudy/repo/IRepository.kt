package com.jidogoon.mvvmstudy.repo

import com.jidogoon.mvvmstudy.data.Photo

interface IRepository {
    fun getPhotos(onReady: (List<Photo>) -> Unit, onError: (Throwable) -> Unit)
    fun getPhoto(photoId: Int, onReady: (Photo) -> Unit, onError: (Throwable) -> Unit)
}