package com.bakharaalief.slootnimeapp.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.bakharaalief.slootnimeapp.data.Resource
import com.bakharaalief.slootnimeapp.domain.model.Media
import com.bakharaalief.slootnimeapp.domain.model.MediaById

interface MediaUseCase {

    fun getListMedia(): LiveData<PagingData<Media>>

    fun getMediaById(id: Int): LiveData<Resource<MediaById>>
}