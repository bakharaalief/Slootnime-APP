package com.bakharaalief.slootnimeapp.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.bakharaalief.slootnimeapp.data.MediaRepository
import com.bakharaalief.slootnimeapp.data.Resource
import com.bakharaalief.slootnimeapp.domain.model.Media
import com.bakharaalief.slootnimeapp.domain.model.MediaById
import javax.inject.Inject

class MediaInteractor @Inject constructor(private val mediaRepository: MediaRepository) :
    MediaUseCase {
    override fun getListMedia(): LiveData<PagingData<Media>> =
        mediaRepository.getListMedia()

    override fun getMediaById(id: Int): LiveData<Resource<MediaById>> =
        mediaRepository.getMediaById(id)
}