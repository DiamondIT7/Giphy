package com.example.giphy.data.repository

import com.example.giphy.data.api.RetrofitInstance
import com.example.giphy.model.DataResult
import retrofit2.Callback

class Repository {

    fun getGifs(callback: Callback<DataResult>) {
        val call = RetrofitInstance.api.getGif()
        call.enqueue(callback)
    }
}