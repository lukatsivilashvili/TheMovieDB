package com.luka.themoviedb.retrofit


import com.luka.themoviedb.models.movies.MovieFinal
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieListService {
    @GET("popular")
    suspend fun getMovies(@Query("api_key") api_key:String?, @Query("page") page: Int) : Response<MovieFinal>
}