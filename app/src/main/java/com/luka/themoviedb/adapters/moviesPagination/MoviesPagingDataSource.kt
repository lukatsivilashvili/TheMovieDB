package com.luka.themoviedb.adapters.moviesPagination

import android.util.Log.d
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.luka.themoviedb.BuildConfig
import com.luka.themoviedb.models.movies.Result
import com.luka.themoviedb.retrofit.MovieListService
import javax.inject.Inject

class MoviesPagingDataSource @Inject constructor(private val repoImpl: MovieListService) :
    PagingSource<Int, Result>() {

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition
    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {

        return try {

            val currentPage = params.key ?: 1
            val response = repoImpl.getMovies(BuildConfig.API_KEY, currentPage)
            val responseData = mutableListOf<Result>()
            val data = response.body()?.results ?: emptyList()
            responseData.addAll(data)
            d("respPage", response.body()?.results.toString())

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