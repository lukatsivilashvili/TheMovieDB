package com.luka.themoviedb.repository.movies.moviesDetails.details

import com.luka.themoviedb.models.movies.moviesDetailsModel.MoviesDetailsFinal
import com.luka.themoviedb.retrofit.NetworkHandler

interface MoviesDetailsRepo {

    suspend fun getMoviesDetails(id:Int):NetworkHandler<MoviesDetailsFinal>
}