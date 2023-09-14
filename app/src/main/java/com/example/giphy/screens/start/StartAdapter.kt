package com.example.giphy.screens.start

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.example.giphy.databinding.ItemGifLayoutBinding
import com.example.giphy.model.DataObject
import com.bumptech.glide.request.target.Target
import com.example.giphy.model.DataResult

class StartAdapter(private val gifs: List<DataObject>) : RecyclerView.Adapter<StartAdapter.StartViewHolder>() {

    private lateinit var mListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    class StartViewHolder(val binding: ItemGifLayoutBinding, listener: OnItemClickListener) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StartViewHolder {
        val binding =
            ItemGifLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StartViewHolder(binding, mListener)
    }

    override fun getItemCount(): Int {
        return gifs.size
    }

    override fun onBindViewHolder(holder: StartViewHolder, position: Int) {
        val data = gifs[position]
        Glide.with(holder.itemView.context)
            .load(data.image.origImage.url)
            .into(holder.binding.ivGif)
    }

}