package com.bakharaalief.graphqlapp.presentation.characterDetail

import androidx.lifecycle.ViewModel
import com.bakharaalief.graphqlapp.domain.usecase.MediaUseCase

class MediaDetailViewModel(private val mediaUseCase: MediaUseCase) : ViewModel() {

    fun getCharactersByIds(id: Int) = mediaUseCase.getMediaById(id)
}