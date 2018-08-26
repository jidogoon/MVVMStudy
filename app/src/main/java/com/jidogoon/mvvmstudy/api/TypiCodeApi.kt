package com.jidogoon.mvvmstudy.api

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.jidogoon.mvvmstudy.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TypiCodeApi {
    companion object {
        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
        private lateinit var service: TypiCodeRemoteService

        fun getService() = service

        fun createApi() {
            service = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(getClient())
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                    .build().create(TypiCodeRemoteService::class.java)
        }

        private fun getClient(): OkHttpClient {
            val client = OkHttpClient.Builder().apply {
                if (BuildConfig.DEBUG) {
                    val interceptor = HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                    addInterceptor(interceptor)
                }
            }
            return client.build()
        }
    }
}