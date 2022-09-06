package com.bakharaalief.graphqlapp.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.bakharaalief.graphqlapp.data.CharacterRepository
import com.bakharaalief.graphqlapp.data.Resource
import com.bakharaalief.graphqlapp.domain.model.Media
import com.bakharaalief.graphqlapp.domain.model.MediaById

class CharacterInteractor(private val characterRepository: CharacterRepository) : CharacterUseCase {
    override fun getCharacters(): LiveData<PagingData<Media>> =
        characterRepository.getCharacters()

    override fun getCharactersByIds(id: Int): LiveData<Resource<MediaById>> = characterRepository.getCharactersByIds(id)
}