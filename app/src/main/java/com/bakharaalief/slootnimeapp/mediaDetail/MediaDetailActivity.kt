package com.bakharaalief.slootnimeapp.mediaDetail

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bakharaalief.slootnimeapp.R
import com.bakharaalief.domain.Resource
import com.bakharaalief.slootnimeapp.databinding.ActivityMediaDetailBinding
import com.bakharaalief.domain.model.Media
import com.bakharaalief.domain.model.MediaById
import com.bumptech.glide.Glide
import com.google.android.material.transition.platform.MaterialSharedAxis
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MediaDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMediaDetailBinding
    private lateinit var characterDetailViewModel: MediaDetailViewModel
    private lateinit var staffAdapter: StaffAdapter
    private lateinit var charactersAdapter: CharactersAdapter

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
        staffAdapter = StaffAdapter()
        binding.staffRv.adapter = staffAdapter
        binding.staffRv.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        charactersAdapter = CharactersAdapter()
        binding.charactersRv.adapter = charactersAdapter
        binding.charactersRv.layoutManager =
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
            .placeholder(R.color.brown)
            .into(binding.bannerImage)

        binding.mediaDetailTitle.text = mediaById.englishTitle
        binding.mediaDetailDesc.text =
            HtmlCompat.fromHtml(mediaById.description, HtmlCompat.FROM_HTML_MODE_COMPACT)

        staffAdapter.submitList(mediaById.staff)
        charactersAdapter.submitList(mediaById.characters)
    }

    companion object {
        const val MEDIA_EXTRA = "CHARACTER_ID"
    }
}