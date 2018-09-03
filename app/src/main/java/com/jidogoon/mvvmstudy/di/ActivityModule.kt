package com.jidogoon.mvvmstudy.di

import android.app.Activity
import com.jidogoon.mvvmstudy.di.scope.UserScope
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: Activity) {
    @Provides
    @UserScope
    fun provideActivity() = activity
}