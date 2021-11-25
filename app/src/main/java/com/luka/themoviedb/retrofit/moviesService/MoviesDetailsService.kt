package com.luka.themoviedb.retrofit.moviesService

import com.luka.themoviedb.BuildConfig.API_KEY
import com.luka.themoviedb.models.movies.moviesDetailsModel.MoviesDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesDetailsService {

    @GET("{movie_id}")
    suspend fun getMoviesDetails(@Path("movie_id") movieId: Int, @Query("api_key") api_Key: String = API_KEY): Response<MoviesDetails>
}