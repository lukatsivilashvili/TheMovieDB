package com.luka.themoviedb.repository.movies.moviesList

import com.luka.themoviedb.models.movies.moviesListModel.MoviesListFirst
import com.luka.themoviedb.retrofit.NetworkHandler

interface MoviesListRepo {

    suspend fun getMoviesList(): NetworkHandler<MoviesListFirst>

}