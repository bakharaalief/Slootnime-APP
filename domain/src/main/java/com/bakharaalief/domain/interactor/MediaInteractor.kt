package com.bakharaalief.domain.interactor

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.bakharaalief.domain.Resource
import com.bakharaalief.domain.model.Media
import com.bakharaalief.domain.model.MediaById
import com.bakharaalief.domain.repository.IMediaRepository
import com.bakharaalief.domain.usecase.MediaUseCase
import javax.inject.Inject

class MediaInteractor @Inject constructor(private val mediaRepository: IMediaRepository) :
    MediaUseCase {
    override fun getListMedia(): LiveData<PagingData<Media>> =
        mediaRepository.getListMedia()

    override fun getMediaById(id: Int): LiveData<Resource<MediaById>> =
        mediaRepository.getMediaById(id)
}