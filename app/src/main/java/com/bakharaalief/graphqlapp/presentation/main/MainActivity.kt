package com.bakharaalief.graphqlapp.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bakharaalief.graphqlapp.databinding.ActivityMainBinding
import com.bakharaalief.graphqlapp.presentation.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: CharactersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViewModel()
        setUpRv()
        getData()
    }

    private fun setUpRv() {
        adapter = CharactersAdapter()
        val footerAdapter = LoadingStateAdapter {
            adapter.retry()
        }

        binding.mediaRv.adapter = adapter.withLoadStateFooter(
            footer = footerAdapter
        )

        val gridLayoutManager = GridLayoutManager(this, 2)

        //make grid from 2 to 1 when offline or no network
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == adapter.itemCount && footerAdapter.itemCount > 0) {
                    2
                } else {
                    1
                }
            }
        }
        binding.mediaRv.layoutManager = gridLayoutManager
    }

    private fun setUpViewModel() {
        val factory = ViewModelFactory.getInstance()
        mainViewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
    }

    private fun getData() {
        mainViewModel.getCharacters().observe(this) {
            adapter.submitData(lifecycle, it)
        }
    }
}