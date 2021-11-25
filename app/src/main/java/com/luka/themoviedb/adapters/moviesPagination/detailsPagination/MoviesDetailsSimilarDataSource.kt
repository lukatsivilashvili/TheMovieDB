package com.luka.themoviedb.adapters.moviesPagination.detailsPagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.luka.themoviedb.models.movies.moviesDetailsSimilarModel.MoviesDetailsSimilarsFinal
import com.luka.themoviedb.retrofit.moviesService.MoviesDetailsSimilarService
import javax.inject.Inject

class MoviesDetailsSimilarDataSource @Inject constructor(
    private val moviesSimilarsService: MoviesDetailsSimilarService,
    private val id: Int
) :
    PagingSource<Int, MoviesDetailsSimilarsFinal>() {

    override fun getRefreshKey(state: PagingState<Int, MoviesDetailsSimilarsFinal>): Int? {
        return state.anchorPosition
    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesDetailsSimilarsFinal> {

        return try {

            val currentPage = params.key ?: 1
            val response =
                moviesSimilarsService.getMoviesDetailsSimilar(movieId = id, currentPage)
            val responseData = mutableListOf<MoviesDetailsSimilarsFinal>()
            val data = response.body()?.moviesDetailsSimilarsFinals ?: emptyList()
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )

        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}