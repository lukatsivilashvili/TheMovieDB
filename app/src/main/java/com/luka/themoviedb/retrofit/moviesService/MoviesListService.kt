package com.luka.themoviedb.retrofit.moviesService


import com.luka.themoviedb.BuildConfig.API_KEY
import com.luka.themoviedb.models.movies.moviesListModel.MoviesListFirst
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesListService {
    @GET("popular")
    suspend fun getMovies(@Query("api_key") api_key:String? = API_KEY, @Query("page") page: Int) : Response<MoviesListFirst>
}