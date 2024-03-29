package com.bakharaalief.slootnimeapp.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.bakharaalief.domain.usecase.MediaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mediaUseCase: MediaUseCase) : ViewModel() {

    fun getCharacters() = mediaUseCase.getListMedia().cachedIn(viewModelScope)
}