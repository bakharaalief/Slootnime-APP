package com.bakharaalief.graphqlapp.presentation.mediaDetail

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bakharaalief.graphqlapp.R
import com.bakharaalief.graphqlapp.data.Resource
import com.bakharaalief.graphqlapp.databinding.ActivityMediaDetailBinding
import com.bakharaalief.graphqlapp.domain.model.Media
import com.bakharaalief.graphqlapp.domain.model.MediaById
import com.bumptech.glide.Glide
import com.google.android.material.transition.platform.MaterialSharedAxis
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MediaDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMediaDetailBinding
    private lateinit var characterDetailViewModel: MediaDetailViewModel
    private lateinit var adapter: StaffAdapter

    private val media: Media by lazy {
        intent.getParcelableExtra(MEDIA_EXTRA) ?: Media(
            0,
            "",
            "",
            "",
            "",
            0,
            emptyList(),
            0
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMediaDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpActionBar()
        setUpInfoInToolbar()
        setUpRv()
        setUpAnimation()
        setUpViewModel()
        getData()
    }

    private fun setUpActionBar() {
        setSupportActionBar(binding.topAppBar)
        supportActionBar?.title = media.englishTitle
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setUpInfoInToolbar() {
        binding.mediaDetailEpisode.text = media.episodes.toString().plus(" episodes")
        binding.mediaDetailReleased.text = media.seasonYear.toString()
    }

    private fun setUpRv() {
        adapter = StaffAdapter()
        binding.staffRv.adapter = adapter
        binding.staffRv.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
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
        characterDetailViewModel.getCharactersByIds(media.id).observe(this) { response ->
            when (response) {
                is Resource.Loading -> {
                    binding.loadingIndicator.visibility = View.VISIBLE
                    binding.mediaDetailInfo.visibility = View.GONE
                }
                is Resource.Success -> {
                    binding.loadingIndicator.visibility = View.GONE
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

        adapter.submitList(mediaById.staff)
    }

    companion object {
        const val MEDIA_EXTRA = "CHARACTER_ID"
    }
}