package com.bakharaalief.graphqlapp.presentation.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bakharaalief.graphqlapp.presentation.ViewModelFactory
import com.bakharaalief.graphqlapp.data.Resource
import com.bakharaalief.graphqlapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: MediaListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViewModel()
        setUpRv()
        getData()
    }

    private fun setUpRv() {
        adapter = MediaListAdapter()
        binding.mediaRv.adapter = adapter
        binding.mediaRv.layoutManager = GridLayoutManager(this, 2)
    }

    private fun setUpViewModel() {
        val factory = ViewModelFactory.getInstance()
        mainViewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
    }

    private fun getData() {
        mainViewModel.getMedia().observe(this) { response ->
            when (response) {
                is Resource.Loading -> {
                    binding.loadingIndicator.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.loadingIndicator.visibility = View.GONE
                    adapter.submitList(response.data)
                }
                is Resource.Error -> {
                    binding.loadingIndicator.visibility = View.GONE
                }
            }
        }
    }
}