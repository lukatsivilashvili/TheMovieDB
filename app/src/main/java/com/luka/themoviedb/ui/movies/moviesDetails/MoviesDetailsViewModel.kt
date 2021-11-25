package com.luka.themoviedb.ui.movies.moviesDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.luka.themoviedb.adapters.moviesPagination.detailsPagination.MoviesDetailsSimilarDataSource
import com.luka.themoviedb.models.movies.moviesDetailsModel.MoviesDetails
import com.luka.themoviedb.models.movies.moviesDetailsSimilarModel.MoviesDetailsSimilarsFinal
import com.luka.themoviedb.repository.movies.moviesDetails.details.MoviesDetailsRepoImplement
import com.luka.themoviedb.retrofit.NetworkHandler
import com.luka.themoviedb.retrofit.moviesService.MoviesDetailsSimilarService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MoviesDetailsViewModel @Inject constructor(private val repoImplDetails: MoviesDetailsRepoImplement, private val serviceSimilar: MoviesDetailsSimilarService) :
    ViewModel() {

    private val _movieDetailsData = MutableLiveData<NetworkHandler<MoviesDetails>>()
    val movieDetailsData: LiveData<NetworkHandler<MoviesDetails>>
        get() = _movieDetailsData


    fun getMovieDetails(id:Int){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val response = repoImplDetails.getMoviesDetails(id)
                _movieDetailsData.postValue(response)
            }
        }
    }



    fun getMoviesDetailsSimilar(id:Int): LiveData<PagingData<MoviesDetailsSimilarsFinal>> {

        val similarList = Pager(PagingConfig(pageSize = 1)) {
            MoviesDetailsSimilarDataSource(serviceSimilar, id)
        }.liveData.cachedIn(viewModelScope)

        return similarList
    }


}