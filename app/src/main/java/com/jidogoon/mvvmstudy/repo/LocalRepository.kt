package com.jidogoon.mvvmstudy.repo

import android.content.Context
import com.google.gson.Gson
import com.jidogoon.mvvmstudy.data.Photo
import kotlinx.coroutines.experimental.launch
import com.google.gson.reflect.TypeToken

class LocalRepository(private val context: Context): IRepository {
    override fun getPhotos(onReady: (MutableList<Photo>) -> Unit, onError: (Throwable) -> Unit) {
        launch {
            try {
                getPhotos()?.let(onReady)
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    override fun getPhoto(photoId: Int, onReady: (Photo) -> Unit, onError: (Throwable) -> Unit) {
        launch {
            try {
                getPhoto()?.let(onReady)
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    private fun getPhotos(): MutableList<Photo>? {
        val jsonData = context.assets.open("photos.json").bufferedReader().use {
            it.readText()
        }

        val listType = object : TypeToken<MutableList<Photo>>() {}.type
        return Gson().fromJson<MutableList<Photo>>(jsonData, listType)
    }

    private fun getPhoto(): Photo? {
        val jsonData = context.assets.open("photo_01.json").bufferedReader().use {
            it.readText()
        }

        return Gson().fromJson(jsonData, Photo::class.java)
    }
}