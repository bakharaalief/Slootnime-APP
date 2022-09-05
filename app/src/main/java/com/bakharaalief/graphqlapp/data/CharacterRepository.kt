package com.bakharaalief.graphqlapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.apollographql.apollo3.ApolloClient
import com.bakharaalief.app.CharactersByIdsQuery
import com.bakharaalief.graphqlapp.data.network.CharactersPagingSource
import com.bakharaalief.graphqlapp.domain.model.Character
import com.bakharaalief.graphqlapp.domain.model.CharacterById
import com.bakharaalief.graphqlapp.domain.repository.ICharacterRepository
import com.bakharaalief.graphqlapp.util.DataMapper.toCharacterIdModel

class CharacterRepository(private val client: ApolloClient) : ICharacterRepository {

    override fun getCharacters(): LiveData<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = {
                CharactersPagingSource(client)
            }
        ).liveData
    }

    override fun getCharactersByIds(id: String): LiveData<Resource<CharacterById>> = liveData {
        emit(Resource.Loading)

        try {
            val array = ArrayList<String>()
            array.add(id)
            val response = client.query(CharactersByIdsQuery(array)).execute()
            val characters = response.data?.charactersByIds?.filterNotNull() ?: emptyList()
            val character = characters.toCharacterIdModel()[0]

            emit(Resource.Success(character))
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }

    companion object {
        @Volatile
        private var instance: CharacterRepository? = null

        fun getInstance(client: ApolloClient): CharacterRepository =
            instance ?: synchronized(this) {
                instance ?: CharacterRepository(client)
            }.also { instance = it }
    }
}