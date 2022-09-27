package com.bakharaalief.slootnimeapp.presentation.mediaDetail

import androidx.lifecycle.ViewModel
import com.bakharaalief.slootnimeapp.domain.usecase.MediaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MediaDetailViewModel @Inject constructor(private val mediaUseCase: MediaUseCase) :
    ViewModel() {

    fun getCharactersByIds(id: Int) = mediaUseCase.getMediaById(id)
}