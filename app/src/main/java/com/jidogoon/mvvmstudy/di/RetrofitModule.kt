package com.jidogoon.mvvmstudy.di

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.jidogoon.mvvmstudy.BuildConfig
import com.jidogoon.mvvmstudy.api.TypiCodeApi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
object RetrofitModule {
    private const val baseUrl = "https://jsonplaceholder.typicode.com/"

    @Provides
    @Reusable
    @Suppress("unused")
    internal fun provideTypiCodeApi(retrofit: Retrofit): TypiCodeApi {
        return retrofit.create(TypiCodeApi::class.java)
    }

    @Provides
    @Reusable
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                .build()
    }

    @Provides
    @Reusable
    fun getHttpClient(): OkHttpClient {
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