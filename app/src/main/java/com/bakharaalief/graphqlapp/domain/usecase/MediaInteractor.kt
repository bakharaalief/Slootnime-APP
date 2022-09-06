package com.bakharaalief.graphqlapp.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.bakharaalief.graphqlapp.data.MediaRepository
import com.bakharaalief.graphqlapp.data.Resource
import com.bakharaalief.graphqlapp.domain.model.Media
import com.bakharaalief.graphqlapp.domain.model.MediaById
import javax.inject.Inject

class MediaInteractor @Inject constructor(private val mediaRepository: MediaRepository) :
    MediaUseCase {
    override fun getListMedia(): LiveData<PagingData<Media>> =
        mediaRepository.getListMedia()

    override fun getMediaById(id: Int): LiveData<Resource<MediaById>> =
        mediaRepository.getMediaById(id)
}