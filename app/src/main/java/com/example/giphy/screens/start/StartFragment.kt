package com.example.giphy.screens.start

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.giphy.R
import com.example.giphy.databinding.FragmentStartBinding
import com.example.giphy.model.DataObject
import kotlinx.coroutines.launch

class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StartAdapter
    private lateinit var viewModel: StartViewModel

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[StartViewModel::class.java]
        recyclerView = binding.rvStart
        val gifs = mutableListOf<DataObject>()
        adapter = StartAdapter(gifs)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(context, 2)

        viewModel.myGifList.observe(viewLifecycleOwner) { gifList ->
            //gifs.clear()
            gifs.addAll(gifList)
            adapter.notifyDataSetChanged()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getGif()
        }

        adapter.setOnItemClickListener(object : StartAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val selectedGifUrl = gifs[position].image.origImage.url
                val bundle = Bundle()
                bundle.putString("selectedGifUrl", selectedGifUrl)

                Navigation.findNavController(binding.root)
                    .navigate(R.id.action_startFragment_to_detailFragment, bundle)
            }
        })

        return binding.root
    }

}