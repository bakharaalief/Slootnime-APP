package com.bakharaalief.graphqlapp.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.bakharaalief.graphqlapp.domain.usecase.CharacterUseCase

class MainViewModel(private val characterUseCase: CharacterUseCase) : ViewModel() {

    fun getCharacters() = characterUseCase.getCharacters().cachedIn(viewModelScope)
}