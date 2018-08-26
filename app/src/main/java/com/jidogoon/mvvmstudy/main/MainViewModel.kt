package com.jidogoon.mvvmstudy.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jidogoon.mvvmstudy.data.Photo
import com.jidogoon.mvvmstudy.repo.IRepository

class MainViewModel(private val repository: IRepository): ViewModel() {
    enum class ViewStatus {
        NOT_SET,
        DONE,
        ERROR
    }

    var viewStatus = MutableLiveData<ViewStatus>().apply {
        value = ViewStatus.NOT_SET
    }

    val isLoading = MutableLiveData<Boolean>()

    private val photos = MutableLiveData<MutableList<Photo>>()

    private val onPhotosReady = { photosResult: MutableList<Photo> ->
        photos.postValue(photosResult)
        isLoading.postValue(false)
        viewStatus.postValue(ViewStatus.DONE)
    }

    private val onRepositoryError = { error: Throwable ->
        viewStatus.postValue(ViewStatus.ERROR)
        error.printStackTrace()
    }

    // for adapter
    fun getPhotosCount(): Int = photos.value?.size ?: 0

    // for adapter
    fun getPhotoItem(position: Int): Photo? = photos.value?.get(position)

    private fun getPhotos() {
        isLoading.value = true
        repository.getPhotos(onPhotosReady, onRepositoryError)
    }

    fun refreshPhotos() {
        photos.value?.clear()
        viewStatus.postValue(ViewStatus.DONE)
        getPhotos()
    }

    init {
        getPhotos()
    }
}