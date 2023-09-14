package com.example.giphy.data.repository

import com.example.giphy.data.api.RetrofitInstance
import com.example.giphy.model.DataResult

class Repository {

    fun getGifs(): retrofit2.Call<DataResult> {
        return RetrofitInstance.api.getGif()
    }
}