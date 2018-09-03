package com.jidogoon.mvvmstudy.di

import com.jidogoon.mvvmstudy.SampleApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SampleAppModule(private val app: SampleApp) {
    @Provides
    @Singleton
    fun provideApp() = app
}