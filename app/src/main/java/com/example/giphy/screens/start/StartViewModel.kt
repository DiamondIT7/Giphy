package com.example.giphy.screens.start

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.giphy.data.repository.Repository
import com.example.giphy.model.DataObject

class StartViewModel: ViewModel() {

    private val repository = Repository()
    private val _myGifList = MutableLiveData<List<DataObject>>()
    val myGifList: LiveData<List<DataObject>> get() = _myGifList

    fun getGif() {
        try {
            val response = repository.getGifs().execute()
            if (response.isSuccessful) {
                _myGifList.postValue(response.body()?.res ?: emptyList())
            }
        } catch (e: Exception) {

        }
    }
}