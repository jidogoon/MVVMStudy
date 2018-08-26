package com.jidogoon.mvvmstudy.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jidogoon.mvvmstudy.data.Photo
import com.jidogoon.mvvmstudy.repo.IRepository

class MainViewModel(private val repository: IRepository): ViewModel() {
    enum class ViewStatus {
        NOT_SET,
        LOADING,
        DONE,
        ERROR
    }

    var viewStatus = MutableLiveData<ViewStatus>().apply {
        value = ViewStatus.NOT_SET
    }

    private val photos = MutableLiveData<List<Photo>>()

    private val onPhotosReady = { photos: List<Photo> ->
        this.photos.postValue(photos)
        viewStatus.postValue(ViewStatus.DONE)
    }

    private val onRepositoryError = { error: Throwable ->
        viewStatus.postValue(ViewStatus.ERROR)
        error.printStackTrace()
    }

    fun getPhotosCount(): Int = photos.value?.size ?: 0

    fun getPhotoItem(position: Int): Photo? = photos.value?.get(position)

    init {
        viewStatus.postValue(ViewStatus.LOADING)
        repository.getPhotos(onPhotosReady, onRepositoryError)
    }
}