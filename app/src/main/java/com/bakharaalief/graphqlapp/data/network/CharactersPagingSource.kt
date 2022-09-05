package com.bakharaalief.graphqlapp.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.bakharaalief.app.CharactersQuery
import com.bakharaalief.graphqlapp.domain.model.Character
import com.bakharaalief.graphqlapp.util.DataMapper.toCharacterModel

class CharactersPagingSource(private val client: ApolloClient) :
    PagingSource<Int, Character>() {

    private val initialPageIndex: Int by lazy { 1 }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {

            val position = params.key ?: initialPageIndex
            val responseData =
                client.query(CharactersQuery(Optional.presentIfNotNull(position))).execute()
            val characters = responseData.data?.characters

            LoadResult.Page(
                data = characters?.results?.filterNotNull()?.toCharacterModel() ?: emptyList(),
                prevKey = if (position == initialPageIndex) null else position - 1,
                nextKey = characters?.info?.next
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }
}