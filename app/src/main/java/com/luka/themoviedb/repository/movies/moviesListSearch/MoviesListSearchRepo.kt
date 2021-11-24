package com.luka.themoviedb.repository.movies.moviesListSearch

import com.luka.themoviedb.models.movies.moviesListModel.MoviesListFirst
import com.luka.themoviedb.retrofit.NetworkHandler

interface MoviesListSearchRepo {

    suspend fun getMoviesSearchList(query:String): NetworkHandler<MoviesListFirst>

}