package com.luka.themoviedb.retrofit.moviesService

import com.luka.themoviedb.BuildConfig
import com.luka.themoviedb.models.movies.moviesListModel.MoviesListFirst
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesListSearchService {
    @GET("movie")
    suspend fun getMoviesSearch(
        @Query("query") query: String,
        @Query("page") page: Int? = 1,
        @Query("api_key") api_key: String? = BuildConfig.API_KEY
    ): Response<MoviesListFirst>
}