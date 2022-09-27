package com.bakharaalief.slootnimeapp.presentation.main

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bakharaalief.slootnimeapp.databinding.ActivityMainBinding
import com.bakharaalief.slootnimeapp.presentation.mediaDetail.MediaDetailActivity
import com.google.android.material.transition.platform.MaterialSharedAxis
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: ListMediaAdapter

    private val spanCount: Int by lazy { 2 }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViewModel()
        setUpAnimation()
        setUpRv()
        getData()

//        binding.toDetail.setOnClickListener {
//            val bundle = ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
//            val detailIntent = Intent(this, MediaDetailActivity::class.java).apply {
//                putExtra(MediaDetailActivity.MEDIA_ID, 1)
//                putExtra(MediaDetailActivity.MEDIA_TITLE, "uhuhy")
//            }
//            startActivity(detailIntent, bundle)
//        }
    }

    private fun setUpRv() {
        adapter = ListMediaAdapter { media ->
            val bundle = ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
            val detailIntent = Intent(this, MediaDetailActivity::class.java).apply {
                putExtra(MediaDetailActivity.MEDIA_EXTRA, media)
            }
            startActivity(detailIntent, bundle)
        }

        val footerAdapter = LoadingStateAdapter {
            adapter.retry()
        }

        binding.mediaRv.adapter = adapter.withLoadStateFooter(
            footer = footerAdapter
        )

        val gridLayoutManager = GridLayoutManager(this, spanCount)

        //make grid from 2 to 1 when offline or no network
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == adapter.itemCount && footerAdapter.itemCount > 0) {
                    spanCount
                } else {
                    1
                }
            }
        }
        binding.mediaRv.layoutManager = gridLayoutManager
    }

    private fun setUpViewModel() {
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    private fun setUpAnimation() {
        val exit = MaterialSharedAxis(MaterialSharedAxis.X, true).apply {
            excludeTarget(android.R.id.statusBarBackground, true)
            excludeTarget(android.R.id.navigationBarBackground, true)
        }
        window.exitTransition = exit
    }

    private fun getData() {
        mainViewModel.getCharacters().observe(this) {
            adapter.submitData(lifecycle, it)
        }
    }
}