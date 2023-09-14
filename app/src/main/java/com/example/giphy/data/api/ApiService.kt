package com.example.giphy.data.api

import com.example.giphy.model.DataResult
import retrofit2.http.GET

interface ApiService {

    @GET("v1/gifs/trending?api_key=hPW4ZKjLyxlUGIIQv0TfgWWywGgSNQnh")
    fun getGif(): retrofit2.Call<DataResult>
}