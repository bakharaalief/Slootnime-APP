package com.bakharaalief.graphqlapp.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.bakharaalief.graphqlapp.data.Resource
import com.bakharaalief.graphqlapp.domain.model.Media
import com.bakharaalief.graphqlapp.domain.model.MediaById

interface CharacterUseCase {

    fun getCharacters(): LiveData<PagingData<Media>>

    fun getCharactersByIds(id: Int): LiveData<Resource<MediaById>>
}