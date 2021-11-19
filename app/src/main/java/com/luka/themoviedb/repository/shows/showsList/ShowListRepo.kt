package com.luka.themoviedb.repository.shows.showsList

import com.luka.themoviedb.models.shows.showsListModel.ShowsListFirst
import com.luka.themoviedb.retrofit.NetworkHandler

interface ShowListRepo {

    suspend fun getShowsList(): NetworkHandler<ShowsListFirst>

}