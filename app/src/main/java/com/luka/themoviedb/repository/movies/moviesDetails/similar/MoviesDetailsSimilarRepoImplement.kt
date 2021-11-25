package com.luka.themoviedb.repository.movies.moviesDetails.similar

import com.luka.themoviedb.models.movies.moviesDetailsSimilarModel.MoviesDetailsSimilarsFirst
import com.luka.themoviedb.retrofit.NetworkHandler
import com.luka.themoviedb.retrofit.moviesService.MoviesDetailsSimilarService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesDetailsSimilarRepoImplement @Inject constructor(private val moviesDetailsSimilarService: MoviesDetailsSimilarService) :
    MoviesDetailsSimilarRepo {

    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    override suspend fun getMoviesDetailsSimilar(id: Int): NetworkHandler<MoviesDetailsSimilarsFirst> =
        withContext(ioDispatcher) {
            try {
                val result =
                    moviesDetailsSimilarService.getMoviesDetailsSimilar(movieId = id)
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