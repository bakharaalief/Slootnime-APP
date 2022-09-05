package com.bakharaalief.graphqlapp.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.bakharaalief.graphqlapp.data.Resource
import com.bakharaalief.graphqlapp.domain.model.Character
import com.bakharaalief.graphqlapp.domain.model.CharacterById

interface CharacterUseCase {

    fun getCharacters(): LiveData<PagingData<Character>>

    fun getCharactersByIds(id: String): LiveData<Resource<CharacterById>>
}