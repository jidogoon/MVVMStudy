package com.jidogoon.mvvmstudy.di

import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [SampleAppModule::class, RetrofitModule::class])
interface SampleAppComponent {
    fun retrofit(): Retrofit
}