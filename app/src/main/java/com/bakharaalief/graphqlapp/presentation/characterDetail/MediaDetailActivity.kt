package com.bakharaalief.graphqlapp.presentation.characterDetail

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.lifecycle.ViewModelProvider
import com.bakharaalief.graphqlapp.R
import com.bakharaalief.graphqlapp.data.Resource
import com.bakharaalief.graphqlapp.databinding.ActivityMediaDetailBinding
import com.bakharaalief.graphqlapp.domain.model.MediaById
import com.bumptech.glide.Glide
import com.google.android.material.transition.platform.MaterialSharedAxis
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MediaDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMediaDetailBinding
    private lateinit var characterDetailViewModel: MediaDetailViewModel

    private val id: Int by lazy { intent.getIntExtra(MEDIA_ID, 0) }
    private val name: String by lazy { intent.getStringExtra(MEDIA_TITLE) ?: "" }

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
        characterDetailViewModel = ViewModelProvider(this)[MediaDetailViewModel::class.java]
    }

    private fun getData() {
        characterDetailViewModel.getCharactersByIds(id).observe(this) { response ->
            when (response) {
                is Resource.Loading -> {
                    binding.loadingIndicator.visibility = View.VISIBLE
                    binding.bannerImage.visibility = View.GONE
                    binding.mediaDetailInfo.visibility = View.GONE
                }
                is Resource.Success -> {
                    binding.loadingIndicator.visibility = View.GONE
                    binding.bannerImage.visibility = View.VISIBLE
                    binding.mediaDetailInfo.visibility = View.VISIBLE
                    setInformation(response.data)
                }
                is Resource.Error -> {
                    binding.loadingIndicator.visibility = View.GONE
                }
            }
        }
    }

    private fun setInformation(mediaById: MediaById) {
        Glide
            .with(this)
            .load(mediaById.bannerImage)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .into(binding.bannerImage)

        binding.mediaDetailTitle.text = mediaById.englishTitle
        binding.mediaDetailDesc.text =
            HtmlCompat.fromHtml(mediaById.description, HtmlCompat.FROM_HTML_MODE_COMPACT)
    }

    companion object {
        const val MEDIA_ID = "CHARACTER_ID"
        const val MEDIA_TITLE = "CHARACTER_TITLE"
    }
}