package com.luka.themoviedb.ui.movies.moviesList

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.luka.themoviedb.adapters.moviesPagination.MoviesPagingDataSource
import com.luka.themoviedb.retrofit.MovieListService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(private val repoImpl: MovieListService) : ViewModel() {

    val moviesList = Pager(PagingConfig(pageSize = 1)) {
        MoviesPagingDataSource(repoImpl)
    }.liveData
}