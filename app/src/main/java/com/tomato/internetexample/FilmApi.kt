package com.tomato.internetexample

import retrofit2.Call
import retrofit2.http.*

interface FilmApi {

    @GET("films")
    fun getFilms(): Call<List<FilmModel>>

//    @POST("films")
//    fun getFilms(@Query("name") name: String): Call<List<FilmModel>>

//    @POST("films/{name}/test1")
//    fun getFilms(@Path("name") name: String): Call<List<FilmModel>>

//    @POST("films/{name}/test1")
//    fun getFilms(@Body name: FilmRequest): Call<List<FilmModel>>
}