package com.bakharaalief.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.bakharaalief.domain.Resource
import com.bakharaalief.domain.model.Media
import com.bakharaalief.domain.model.MediaById

interface MediaUseCase {

    fun getListMedia(): LiveData<PagingData<Media>>

    fun getMediaById(id: Int): LiveData<Resource<MediaById>>
}