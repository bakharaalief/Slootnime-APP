package com.bakharaalief.graphqlapp.presentation.main

import androidx.lifecycle.ViewModel
import com.bakharaalief.graphqlapp.data.MediaRepository

class MainViewModel(private val mediaRepository: MediaRepository) : ViewModel() {

    fun getMedia() = mediaRepository.getMedia()
}