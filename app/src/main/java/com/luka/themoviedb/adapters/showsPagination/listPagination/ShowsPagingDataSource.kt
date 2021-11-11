package com.luka.themoviedb.adapters.showsPagination.listPagination

import android.util.Log.d
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.luka.themoviedb.BuildConfig
import com.luka.themoviedb.models.movies.moviesListModel.MoviesListFinal
import com.luka.themoviedb.models.movies.showsListModel.ShowsListFinal
import com.luka.themoviedb.retrofit.MovieListService
import com.luka.themoviedb.retrofit.ShowsListService
import javax.inject.Inject

class ShowsPagingDataSource @Inject constructor(private val repoImpl: ShowsListService) :
    PagingSource<Int, ShowsListFinal>() {

    override fun getRefreshKey(state: PagingState<Int, ShowsListFinal>): Int? {
        return state.anchorPosition
    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ShowsListFinal> {

        return try {

            val currentPage = params.key ?: 1
            val response = repoImpl.getShows(BuildConfig.API_KEY, currentPage)
            val responseData = mutableListOf<ShowsListFinal>()
            val data = response.body()?.showsListFinals ?: emptyList()
            responseData.addAll(data)
            d("respPage", response.body()?.showsListFinals.toString())

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