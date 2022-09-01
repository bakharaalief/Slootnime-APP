package com.bakharaalief.graphqlapp.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.apollographql.apollo3.ApolloClient
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
}