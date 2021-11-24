package com.luka.themoviedb.ui.movies.moviesList

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.luka.themoviedb.adapters.moviesPagination.listPagination.MoviesListPagingDataSource
import com.luka.themoviedb.adapters.moviesPagination.listPagination.MoviesListSearchPagingDataSource
import com.luka.themoviedb.models.movies.moviesListModel.MoviesListFinal
import com.luka.themoviedb.retrofit.moviesService.MoviesListSearchService
import com.luka.themoviedb.retrofit.moviesService.MoviesListService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(
    private val moviesListRepoImpl: MoviesListService,
    private val moviesListSearchRepoImpl: MoviesListSearchService
) : ViewModel() {

    val moviesList = Pager(PagingConfig(pageSize = 1)) {
        MoviesListPagingDataSource(moviesListRepoImpl)
    }.liveData.cachedIn(viewModelScope)


    fun getMoviesListSearch(query: String): LiveData<PagingData<MoviesListFinal>> {

        val moviesListSearch = Pager(PagingConfig(pageSize = 1)) {
            MoviesListSearchPagingDataSource(moviesListSearchRepoImpl, query)
        }.liveData.cachedIn(viewModelScope)

        return moviesListSearch
    }
}