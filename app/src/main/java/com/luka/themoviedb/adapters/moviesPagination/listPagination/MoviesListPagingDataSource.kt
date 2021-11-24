package com.luka.themoviedb.adapters.moviesPagination.listPagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.luka.themoviedb.BuildConfig
import com.luka.themoviedb.models.movies.moviesListModel.MoviesListFinal
import com.luka.themoviedb.retrofit.moviesService.MoviesListService
import javax.inject.Inject

class MoviesListPagingDataSource @Inject constructor(private val repoImpl: MoviesListService) :
    PagingSource<Int, MoviesListFinal>() {

    override fun getRefreshKey(state: PagingState<Int, MoviesListFinal>): Int? {
        return state.anchorPosition
    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesListFinal> {

        return try {

            val currentPage = params.key ?: 1
            val response = repoImpl.getMovies(BuildConfig.API_KEY, currentPage)
            val responseData = mutableListOf<MoviesListFinal>()
            val data = response.body()?.moviesListFinals ?: emptyList()
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