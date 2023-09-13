package com.example.giphy

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.giphy.data.api.ApiService
import com.example.giphy.databinding.ActivityMainBinding
import com.example.giphy.model.DataObject
import com.example.giphy.model.DataResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.rvMain
        val gifs = mutableListOf<DataObject>()
        val adapter = RootAdapter(gifs)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        adapter.setOnItemClickListener(object : RootAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra("url", gifs[position].image.origImage.url)
                startActivity(intent)
            }
        })

        // retrofit instance
        val retrofit = Retrofit.Builder().
            baseUrl("https://api.giphy.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // service
        val retrofitService = retrofit.create(ApiService::class.java)
        retrofitService.getGif().enqueue(object : Callback<DataResult?> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<DataResult?>, response: Response<DataResult?>) {
                val body = response.body()
                if (body == null){
                    Log.d("MainActivity", "onResponse: No response...")
                }
                gifs.addAll(body!!.res)
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<DataResult?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}