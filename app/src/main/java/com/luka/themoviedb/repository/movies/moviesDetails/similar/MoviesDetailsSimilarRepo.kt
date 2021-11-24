package com.luka.themoviedb.repository.movies.moviesDetails.similar

import com.luka.themoviedb.models.movies.moviesDetailsSimilarModel.MoviesDetailsSimilarsFirst
import com.luka.themoviedb.retrofit.NetworkHandler

interface MoviesDetailsSimilarRepo {

    suspend fun getMoviesDetailsSimilar(id:Int): NetworkHandler<MoviesDetailsSimilarsFirst>
}