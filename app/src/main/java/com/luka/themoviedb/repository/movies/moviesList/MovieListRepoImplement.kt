package com.luka.themoviedb.repository.movies.moviesList

import com.luka.themoviedb.BuildConfig
import com.luka.themoviedb.models.movies.MovieFinal
import com.luka.themoviedb.retrofit.MovieListService
import com.luka.themoviedb.retrofit.NetworkHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieListRepoImplement @Inject constructor(private val movieListService: MovieListService) :
    MoviesListRepo {

    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    override suspend fun getMoviesList(): NetworkHandler<MovieFinal> =

        withContext(ioDispatcher) {
            try {
                val result = movieListService.getMovies(BuildConfig.API_KEY,1)
                if (result.isSuccessful) {
                    NetworkHandler.Success(result.body()!!)
                } else {
                    NetworkHandler.Error("Request failed")
                }
            } catch (e: Exception) {
                NetworkHandler.Error("Request failed")
            }
        }

}