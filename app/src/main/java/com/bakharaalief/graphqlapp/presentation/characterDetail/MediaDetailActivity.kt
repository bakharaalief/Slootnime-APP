package com.bakharaalief.graphqlapp.presentation.characterDetail

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bakharaalief.graphqlapp.data.Resource
import com.bakharaalief.graphqlapp.databinding.ActivityMediaDetailBinding
import com.bakharaalief.graphqlapp.presentation.ViewModelFactory
import com.google.android.material.transition.platform.MaterialSharedAxis

class MediaDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMediaDetailBinding
    private lateinit var characterDetailViewModel: MediaDetailViewModel

    private val id: Int by lazy { intent.getIntExtra(CHARACTER_ID, 0) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMediaDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpActionBar()
        setUpAnimation()
        setUpViewModel()
        getData()
    }

    private fun setUpActionBar() {
        supportActionBar?.title = id.toString()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> true
        }
    }

    private fun setUpAnimation() {
        val enter = MaterialSharedAxis(MaterialSharedAxis.X, true).apply {
            excludeTarget(android.R.id.statusBarBackground, true)
            excludeTarget(android.R.id.navigationBarBackground, true)
        }
        window.enterTransition = enter
        window.allowEnterTransitionOverlap = true
    }

    private fun setUpViewModel() {
        val factory = ViewModelFactory.getInstance()
        characterDetailViewModel =
            ViewModelProvider(this, factory)[MediaDetailViewModel::class.java]
    }

    private fun getData() {
        characterDetailViewModel.getCharactersByIds(id).observe(this) { response ->
            when (response) {
                is Resource.Loading -> {
                    binding.loadingIndicator.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.loadingIndicator.visibility = View.GONE
                    binding.characterDetailInfo.visibility = View.VISIBLE
                }
                is Resource.Error -> {
                    binding.loadingIndicator.visibility = View.GONE
                }
            }
        }
    }

    companion object {
        const val CHARACTER_ID = "CHARACTER_ID"
    }
}