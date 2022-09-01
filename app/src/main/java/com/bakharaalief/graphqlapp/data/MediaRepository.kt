package com.bakharaalief.graphqlapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.apollographql.apollo3.ApolloClient
import com.bakharaalief.app.CharactersQuery

class MediaRepository(private val client: ApolloClient) {

    fun getMedia(): LiveData<Resource<List<CharactersQuery.Result?>?>> = liveData {
        emit(Resource.Loading)

        try {
            val response = client.query(CharactersQuery()).execute()
            val data = response.data?.characters?.results
            emit(Resource.Success(data))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }
}