package com.luka.themoviedb.ui.shows.showsList

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.luka.themoviedb.adapters.showsPagination.listPagination.ShowsPagingDataSource
import com.luka.themoviedb.retrofit.ShowsListService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShowsListViewModel @Inject constructor(private val repoImpl: ShowsListService) : ViewModel() {

    val showsList = Pager(PagingConfig(pageSize = 1)) {
        ShowsPagingDataSource(repoImpl)
    }.liveData
}