package com.tomato.internetexample

import android.app.Application
import com.tomato.internetexample.BuildConfig.DEBUG
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App: Application() {

    lateinit var api: FilmApi

    override fun onCreate() {
        super.onCreate()
        instance = this

        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val response = chain.proceed(
                    chain.request()
                        .newBuilder()
                        .addHeader("Authorization", "Bearer some_token")
                        .build()
                )

                return@addInterceptor response
            }
            .addInterceptor (
                HttpLoggingInterceptor().apply {
                    if (DEBUG) {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                }
            )
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        api = retrofit.create(FilmApi::class.java)
    }

    companion object {
        const val BASE_URL = "https://my-json-server.typicode.com/shustreek/demo/"

        lateinit var instance: App
            private set
    }
}