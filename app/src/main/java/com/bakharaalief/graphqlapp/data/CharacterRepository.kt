package com.bakharaalief.graphqlapp.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.apollographql.apollo3.ApolloClient
import com.bakharaalief.app.CharactersByIdsQuery
import com.bakharaalief.app.CharactersQuery
import com.bakharaalief.graphqlapp.data.network.CharactersPagingSource

class CharacterRepository(private val client: ApolloClient) {

    fun getCharacters(): LiveData<PagingData<CharactersQuery.Result>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = {
                CharactersPagingSource(client)
            }
        ).liveData
    }

    fun getCharactersByIds(id: String): LiveData<Resource<CharactersByIdsQuery.CharactersById?>> =
        liveData {
            emit(Resource.Loading)

            try {
                val array = ArrayList<String>()
                array.add(id)
                val response = client.query(CharactersByIdsQuery(array)).execute()
                val character = response.data?.charactersByIds?.filterNotNull()?.get(0)

                emit(Resource.Success(character))
            } catch (e: Exception) {
                Log.d("test", "$e")
                emit(Resource.Error(e.toString()))
            }
        }
}