package com.bakharaalief.graphqlapp.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.bakharaalief.graphqlapp.data.CharacterRepository
import com.bakharaalief.graphqlapp.data.Resource
import com.bakharaalief.graphqlapp.domain.model.Character
import com.bakharaalief.graphqlapp.domain.model.CharacterById

class CharacterInteractor(private val characterRepository: CharacterRepository) : CharacterUseCase {
    override fun getCharacters(): LiveData<PagingData<Character>> =
        characterRepository.getCharacters()

    override fun getCharactersByIds(id: String): LiveData<Resource<CharacterById>> =
        characterRepository.getCharactersByIds(id)
}