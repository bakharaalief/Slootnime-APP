package com.bakharaalief.graphqlapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.apollographql.apollo3.ApolloClient
import com.bakharaalief.app.MediaListQuery

class MediaRepository(private val client: ApolloClient) {

    fun getMedia(): LiveData<Resource<List<MediaListQuery.Medium?>?>> = liveData {
        emit(Resource.Loading)

        try {
            val response = client.query(MediaListQuery()).execute()
            val data = response.data?.Page?.media
            emit(Resource.Success(data))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }
}