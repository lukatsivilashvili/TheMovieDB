package com.luka.themoviedb.adapters.moviesPagination.listPagination

import android.util.Log.d
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.luka.themoviedb.BuildConfig
import com.luka.themoviedb.models.movies.moviesListModel.MoviesListFinal
import com.luka.themoviedb.retrofit.MovieListService
import javax.inject.Inject

class MoviesPagingDataSource @Inject constructor(private val repoImpl: MovieListService) :
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
            d("respPage", response.body()?.moviesListFinals.toString())

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