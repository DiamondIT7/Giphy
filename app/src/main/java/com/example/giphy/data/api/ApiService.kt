package com.example.giphy.data.api

import android.telecom.Call
import com.example.giphy.model.DataResult
import com.example.giphy.model.Gif
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v1/gifs/trending?api_key=hPW4ZKjLyxlUGIIQv0TfgWWywGgSNQnh")
    fun getGif(): retrofit2.Call<DataResult>


    /*@GET("v1/gifs/trending")
    fun trendGif(
        @Query("api_key") apiKey: String,
        @Query("q") query: String,
        @Query("limit") limit: Int
    ): Call<Response<>>

    @GET("v1/gifs/search")
    fun searchGif(
        @Query("api_key") apiKey: String,
        @Query("q") query: String,
        @Query("limit") limit: Int
    ): Response<Gif>*/
}