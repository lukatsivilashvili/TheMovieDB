package com.luka.themoviedb.retrofit.showsService

import com.luka.themoviedb.models.shows.showsListModel.ShowsListFirst
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ShowsListService {
    @GET("popular")
    suspend fun getShows(@Query("api_key") api_key:String?, @Query("page") page: Int) : Response<ShowsListFirst>
}