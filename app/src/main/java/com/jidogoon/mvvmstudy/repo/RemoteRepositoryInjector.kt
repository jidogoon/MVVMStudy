package com.jidogoon.mvvmstudy.repo

import com.jidogoon.mvvmstudy.di.RetrofitModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitModule::class])
interface RemoteRepositoryInjector {
    fun inject(remoteRepository: RemoteRepository)

    @Component.Builder
    interface Builder {
        fun build(): RemoteRepositoryInjector
        fun retrofitModule(retrofitModule: RetrofitModule): Builder
    }
}