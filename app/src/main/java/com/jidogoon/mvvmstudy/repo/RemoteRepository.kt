package com.jidogoon.mvvmstudy.repo

import com.jidogoon.mvvmstudy.api.TypiCodeApi
import com.jidogoon.mvvmstudy.data.Photo
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

class RemoteRepository: BaseRepository(), IRepository {
    @Inject
    lateinit var typiCodeApi: TypiCodeApi

    override fun getPhotos(onReady: (MutableList<Photo>) -> Unit, onError: (Throwable) -> Unit) {
        launch {
            try {
                val result = typiCodeApi.getPhotos().await().toMutableList()
                //val result = TypiCodeApi.getService().getPhotos().await().toMutableList()
                onReady(result)
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    override fun getPhoto(photoId: Int, onReady: (Photo) -> Unit, onError: (Throwable) -> Unit) {
        launch {
            try {
                val result = typiCodeApi.getPhoto(photoId).await()
                onReady(result)
            } catch (e: Exception) {
                onError(e)
            }
        }
    }
}