package com.bakharaalief.graphqlapp.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.bakharaalief.graphqlapp.data.Resource
import com.bakharaalief.graphqlapp.domain.model.Media
import com.bakharaalief.graphqlapp.domain.model.MediaById

interface MediaUseCase {

    fun getListMedia(): LiveData<PagingData<Media>>

    fun getMediaById(id: Int): LiveData<Resource<MediaById>>
}