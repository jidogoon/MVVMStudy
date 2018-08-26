package com.jidogoon.mvvmstudy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jidogoon.mvvmstudy.main.MainViewModel
import com.jidogoon.mvvmstudy.repo.IRepository

class ViewModelFactory(): ViewModelProvider.Factory {
    private lateinit var repository: IRepository
    constructor(repository: IRepository): this() {
        this.repository = repository
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(repository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}