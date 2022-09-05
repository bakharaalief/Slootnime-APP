package com.bakharaalief.graphqlapp.presentation.characterDetail

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bakharaalief.graphqlapp.R
import com.bakharaalief.graphqlapp.data.Resource
import com.bakharaalief.graphqlapp.databinding.ActivityCharacterDetailBinding
import com.bakharaalief.graphqlapp.presentation.ViewModelFactory
import com.bumptech.glide.Glide
import com.google.android.material.transition.platform.MaterialSharedAxis

class CharacterDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharacterDetailBinding
    private lateinit var characterDetailViewModel: CharacterDetailViewModel

    private val id: String by lazy { intent.getStringExtra(CHARACTER_ID) ?: "" }
    private val name: String by lazy { intent.getStringExtra(CHARACTER_NAME) ?: "" }
    private val image: String by lazy { intent.getStringExtra(CHARACTER_IMAGE) ?: "" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCharacterDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpActionBar()
        setUpAnimation()
        setUpViewModel()
        setPhoto()
        getData()
    }

    private fun setUpActionBar() {
        supportActionBar?.title = name
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
            ViewModelProvider(this, factory)[CharacterDetailViewModel::class.java]
    }

    private fun setPhoto() {
        Glide
            .with(this)
            .load(image)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .into(binding.characterDetailImage)
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
                    binding.characterDetailName.text = response.data.name
                    binding.characterDetailGender.text = response.data.gender
                    binding.characterDetailSpecies.text = response.data.species
                }
                is Resource.Error -> {
                    binding.loadingIndicator.visibility = View.GONE
                }
            }
        }
    }

    companion object {
        const val CHARACTER_ID = "CHARACTER_ID"
        const val CHARACTER_NAME = "CHARACTER_NAME"
        const val CHARACTER_IMAGE = "CHARACTER_IMAGE"
    }
}