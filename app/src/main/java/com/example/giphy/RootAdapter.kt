package com.example.giphy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.giphy.databinding.ItemGifLayoutBinding
import com.example.giphy.model.DataObject

class RootAdapter(private val gifs: List<DataObject>) : RecyclerView.Adapter<RootAdapter.RootViewHolder>() {

    private lateinit var mListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    class RootViewHolder(val binding: ItemGifLayoutBinding, listener: OnItemClickListener) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RootViewHolder {
        val binding =
            ItemGifLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RootViewHolder(binding, mListener)
    }

    override fun getItemCount(): Int {
        return gifs.size
    }

    override fun onBindViewHolder(holder: RootViewHolder, position: Int) {
        val data = gifs[position]
        Glide.with(holder.itemView.context).load(data.image.origImage.url)
            .into(holder.binding.ivGif)
    }

}