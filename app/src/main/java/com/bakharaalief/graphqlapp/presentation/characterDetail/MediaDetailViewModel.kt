package com.bakharaalief.graphqlapp.presentation.characterDetail

import androidx.lifecycle.ViewModel
import com.bakharaalief.graphqlapp.domain.usecase.MediaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MediaDetailViewModel @Inject constructor(private val mediaUseCase: MediaUseCase) :
    ViewModel() {

    fun getCharactersByIds(id: Int) = mediaUseCase.getMediaById(id)
}