package com.luka.themoviedb.repository.shows.showsList

import com.luka.themoviedb.BuildConfig
import com.luka.themoviedb.models.shows.showsListModel.ShowsListFirst
import com.luka.themoviedb.retrofit.NetworkHandler
import com.luka.themoviedb.retrofit.showsService.ShowsListService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ShowListRepoImplement @Inject constructor(private val showListService: ShowsListService) :
    ShowListRepo {

    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    override suspend fun getShowsList(): NetworkHandler<ShowsListFirst> =

        withContext(ioDispatcher) {
            try {
                val result = showListService.getShows(BuildConfig.API_KEY, 1)
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