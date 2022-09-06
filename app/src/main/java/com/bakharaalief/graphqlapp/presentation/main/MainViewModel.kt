package com.bakharaalief.graphqlapp.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.bakharaalief.graphqlapp.domain.usecase.MediaUseCase

class MainViewModel(private val mediaUseCase: MediaUseCase) : ViewModel() {

    fun getCharacters() = mediaUseCase.getListMedia().cachedIn(viewModelScope)
}