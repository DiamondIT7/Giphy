package com.example.giphy.screens.start

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.giphy.data.repository.Repository
import com.example.giphy.model.DataObject
import com.example.giphy.model.DataResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StartViewModel: ViewModel() {

    private val repository = Repository()
    private val _myGifList = MutableLiveData<List<DataObject>>()
    val myGifList: LiveData<List<DataObject>> get() = _myGifList

    fun getGif() {
        repository.getGifs(object : Callback<DataResult> {
            override fun onResponse(call: Call<DataResult>, response: Response<DataResult>) {
                if (response.isSuccessful) {
                    _myGifList.postValue(response.body()?.res ?: emptyList())
                }
            }

            override fun onFailure(call: Call<DataResult>, t: Throwable) {

            }
        })
    }
}