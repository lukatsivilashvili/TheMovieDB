package com.luka.themoviedb.repository.movies.moviesListSearch

import com.luka.themoviedb.models.movies.moviesListModel.MoviesListFirst
import com.luka.themoviedb.retrofit.NetworkHandler
import com.luka.themoviedb.retrofit.moviesService.MoviesListSearchService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesListSearchRepoImplement @Inject constructor(private val moviesSearchListService: MoviesListSearchService) :
    MoviesListSearchRepo {

    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    override suspend fun getMoviesSearchList(query: String): NetworkHandler<MoviesListFirst> =
        withContext(ioDispatcher) {
            try {
                val result = moviesSearchListService.getMoviesSearch(query)
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
