package com.bakhdev.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.bakharaalief.app.ListMediaQuery
import com.bakharaalief.domain.model.Media
import com.bakhdev.data.util.DataMapper.toMediaModel

class ListMediaPagingSource(private val client: ApolloClient) :
    PagingSource<Int, Media>() {

    private val initialPageIndex: Int by lazy { 1 }

    override fun getRefreshKey(state: PagingState<Int, Media>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Media> {
        return try {

            val position = params.key ?: initialPageIndex
            val responseData =
                client.query(ListMediaQuery(Optional.presentIfNotNull(position))).execute()
            val listMedia = responseData.data?.Page?.media
            val pageInfo = responseData.data?.Page?.pageInfo

            LoadResult.Page(
                data = listMedia?.filterNotNull()?.toMediaModel() ?: emptyList(),
                prevKey = if (position == initialPageIndex) null else position - 1,
                nextKey = if (pageInfo?.hasNextPage == false) null else position + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }
}