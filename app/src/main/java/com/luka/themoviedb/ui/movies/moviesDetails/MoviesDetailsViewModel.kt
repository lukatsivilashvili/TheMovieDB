package com.luka.themoviedb.ui.movies.moviesDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luka.themoviedb.models.movies.moviesDetailsModel.MoviesDetailsFinal
import com.luka.themoviedb.repository.movies.moviesDetails.MoviesDetailsRepoImplement
import com.luka.themoviedb.retrofit.NetworkHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MoviesDetailsViewModel @Inject constructor(private val repoImpl: MoviesDetailsRepoImplement) :
    ViewModel() {

    private val _movieDetailsData = MutableLiveData<NetworkHandler<MoviesDetailsFinal>>()
    val movieDetailsData: LiveData<NetworkHandler<MoviesDetailsFinal>>
        get() = _movieDetailsData


    fun getMovieDetails(id:Int){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val response = repoImpl.getMoviesDetails(id)
                _movieDetailsData.postValue(response)
            }
        }
    }

}