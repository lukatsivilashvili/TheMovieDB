package com.luka.themoviedb.retrofit.moviesService

import com.luka.themoviedb.BuildConfig
import com.luka.themoviedb.models.movies.moviesDetailsSimilarModel.MoviesDetailsSimilarsFirst
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesDetailsSimilarService {

    @GET("{movie_id}/similar")
    suspend fun getMoviesDetailsSimilar(@Path("movie_id") movieId: Int?, @Query("api_key") api_Key: String? = BuildConfig.API_KEY, @Query("page") page: Int): Response<MoviesDetailsSimilarsFirst>
}