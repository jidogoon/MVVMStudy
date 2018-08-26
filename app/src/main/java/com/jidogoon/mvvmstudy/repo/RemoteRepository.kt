package com.jidogoon.mvvmstudy.repo

import com.jidogoon.mvvmstudy.api.TypiCodeApi
import com.jidogoon.mvvmstudy.data.Photo
import kotlinx.coroutines.experimental.launch

class RemoteRepository: IRepository {
    override fun getPhotos(onReady: (List<Photo>) -> Unit, onError: (Throwable) -> Unit) {
        launch {
            try {
                val result = TypiCodeApi.getService().getPhotos().await()
                onReady(result)
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    override fun getPhoto(photoId: Int, onReady: (Photo) -> Unit, onError: (Throwable) -> Unit) {
        launch {
            try {
                val result = TypiCodeApi.getService().getPhoto(photoId).await()
                onReady(result)
            } catch (e: Exception) {
                onError(e)
            }
        }
    }
}