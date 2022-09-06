package com.bakharaalief.graphqlapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.bakharaalief.app.MediaByIdQuery
import com.bakharaalief.graphqlapp.data.network.ListMediaPagingSource
import com.bakharaalief.graphqlapp.domain.model.Media
import com.bakharaalief.graphqlapp.domain.model.MediaById
import com.bakharaalief.graphqlapp.domain.repository.IMediaRepository
import com.bakharaalief.graphqlapp.util.DataMapper.toMediaByIdModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MediaRepository @Inject constructor(private val client: ApolloClient) : IMediaRepository {

    override fun getListMedia(): LiveData<PagingData<Media>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = {
                ListMediaPagingSource(client)
            }
        ).liveData
    }

    override fun getMediaById(id: Int): LiveData<Resource<MediaById>> = liveData {
        emit(Resource.Loading)

        try {
            val response = client.query(MediaByIdQuery(Optional.presentIfNotNull(id))).execute()
            val media = response.data?.Page?.media?.filterNotNull()?.get(0)?.toMediaByIdModel()!!

            emit(Resource.Success(media))
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }
}