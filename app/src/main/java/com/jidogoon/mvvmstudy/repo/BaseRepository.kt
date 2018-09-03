package com.jidogoon.mvvmstudy.repo

import com.jidogoon.mvvmstudy.di.RetrofitModule

abstract class BaseRepository {
    private val injector: RemoteRepositoryInjector = DaggerRemoteRepositoryInjector.
            builder().retrofitModule(RetrofitModule).build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is RemoteRepository -> injector.inject(this)
        }
    }

}