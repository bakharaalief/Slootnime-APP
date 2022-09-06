package com.bakharaalief.graphqlapp.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.bakharaalief.graphqlapp.data.Resource
import com.bakharaalief.graphqlapp.domain.model.Media
import com.bakharaalief.graphqlapp.domain.model.MediaById

interface ICharacterRepository {

    fun getCharacters(): LiveData<PagingData<Media>>

    fun getCharactersByIds(id: Int): LiveData<Resource<MediaById>>
}