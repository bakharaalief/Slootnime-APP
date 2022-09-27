package com.bakharaalief.slootnimeapp.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.bakharaalief.slootnimeapp.data.Resource
import com.bakharaalief.slootnimeapp.domain.model.Media
import com.bakharaalief.slootnimeapp.domain.model.MediaById

interface IMediaRepository {

    fun getListMedia(): LiveData<PagingData<Media>>

    fun getMediaById(id: Int): LiveData<Resource<MediaById>>
}