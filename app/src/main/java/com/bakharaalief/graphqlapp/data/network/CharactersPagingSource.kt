package com.bakharaalief.graphqlapp.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Input
import com.bakharaalief.app.CharactersQuery

class CharactersPagingSource(private val client: ApolloClient) :
    PagingSource<Int, CharactersQuery.Result>() {

    private val initialPageIndex: Int = 1

    override fun getRefreshKey(state: PagingState<Int, CharactersQuery.Result>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharactersQuery.Result> {
        return try {

            val position = params.key ?: initialPageIndex
            val responseData = client.query(CharactersQuery(Input.optional(position))).execute()
            val characters = responseData.data?.characters

            LoadResult.Page(
                data = characters?.results?.filterNotNull() ?: emptyList(),
                prevKey = if (position == initialPageIndex) null else position - 1,
                nextKey = characters?.info?.next
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }
}