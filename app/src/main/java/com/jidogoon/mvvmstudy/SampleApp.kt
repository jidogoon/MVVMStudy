package com.jidogoon.mvvmstudy

import android.app.Application
import com.jidogoon.mvvmstudy.api.TypiCodeApi

class SampleApp: Application() {
    override fun onCreate() {
        super.onCreate()
        TypiCodeApi.createApi()
    }
}