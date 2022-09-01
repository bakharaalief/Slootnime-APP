package com.bakharaalief.graphqlapp.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.bakharaalief.graphqlapp.data.CharacterRepository

class MainViewModel(private val characterRepository: CharacterRepository) : ViewModel() {

    fun getCharacters() = characterRepository.getCharacters().cachedIn(viewModelScope)
}