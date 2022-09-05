package com.bakharaalief.graphqlapp.presentation.characterDetail

import androidx.lifecycle.ViewModel
import com.bakharaalief.graphqlapp.domain.usecase.CharacterUseCase

class CharacterDetailViewModel(private val characterUseCase: CharacterUseCase) : ViewModel() {

    fun getCharactersByIds(id: String) = characterUseCase.getCharactersByIds(id)
}