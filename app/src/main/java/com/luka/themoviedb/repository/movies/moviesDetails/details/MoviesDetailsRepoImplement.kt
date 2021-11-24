package com.luka.themoviedb.repository.movies.moviesDetails.details

import com.luka.themoviedb.models.movies.moviesDetailsModel.MoviesDetailsFinal
import com.luka.themoviedb.retrofit.NetworkHandler
import com.luka.themoviedb.retrofit.moviesService.MoviesDetailsService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesDetailsRepoImplement @Inject constructor(private val moviesDetailsService: MoviesDetailsService) :
    MoviesDetailsRepo {

    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    override suspend fun getMoviesDetails(id: Int): NetworkHandler<MoviesDetailsFinal> {
        return withContext(ioDispatcher) {
            try {
                val result = moviesDetailsService.getMoviesDetails(movieId = id)
                NetworkHandler.Success(result.body()!!)
            } catch (e: Exception) {
                NetworkHandler.Error(e.toString())
            }
        }
    }
}