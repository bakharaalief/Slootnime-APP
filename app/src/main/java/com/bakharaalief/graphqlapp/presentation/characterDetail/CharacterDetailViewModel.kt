package com.bakharaalief.graphqlapp.presentation.characterDetail

import androidx.lifecycle.ViewModel
import com.bakharaalief.graphqlapp.data.CharacterRepository

class CharacterDetailViewModel(private val characterRepository: CharacterRepository) : ViewModel() {

    fun getCharactersByIds(id: String) = characterRepository.getCharactersByIds(id)
}