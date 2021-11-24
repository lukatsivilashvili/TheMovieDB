package com.luka.themoviedb.di

import androidx.viewbinding.BuildConfig
import com.luka.themoviedb.repository.movies.moviesDetails.details.MoviesDetailsRepo
import com.luka.themoviedb.repository.movies.moviesDetails.details.MoviesDetailsRepoImplement
import com.luka.themoviedb.repository.movies.moviesDetails.similar.MoviesDetailsSimilarRepo
import com.luka.themoviedb.repository.movies.moviesDetails.similar.MoviesDetailsSimilarRepoImplement
import com.luka.themoviedb.repository.movies.moviesList.MovieListRepoImplement
import com.luka.themoviedb.repository.movies.moviesList.MoviesListRepo
import com.luka.themoviedb.repository.shows.showsList.ShowListRepo
import com.luka.themoviedb.repository.shows.showsList.ShowListRepoImplement
import com.luka.themoviedb.retrofit.moviesService.MoviesDetailsService
import com.luka.themoviedb.retrofit.moviesService.MoviesDetailsSimilarService
import com.luka.themoviedb.retrofit.moviesService.MoviesListService
import com.luka.themoviedb.retrofit.showsService.ShowsListService
import com.luka.themoviedb.utils.Constants.BASE_URL_MOVIES
import com.luka.themoviedb.utils.Constants.BASE_URL_SHOWS
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    private fun interceptorClient(): OkHttpClient {
        val builder = OkHttpClient.Builder().addInterceptor(Interceptor { chain ->
            chain.request().url
            val request = chain.request().newBuilder()
            val response = chain.proceed(request.build())
            response
        })

        if (BuildConfig.BUILD_TYPE == "release") {
            builder.addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
        }
        return builder.build()
    }


    @Provides
    @Singleton
    fun movieListService(): MoviesListService =
        Retrofit.Builder()
            .baseUrl(BASE_URL_MOVIES)
            .addConverterFactory(GsonConverterFactory.create())
            .client(interceptorClient())
            .build()
            .create(MoviesListService::class.java)


    @Provides
    @Singleton
    fun addMovieListRepo(moviesListService: MoviesListService): MoviesListRepo =
        MovieListRepoImplement(moviesListService)


    @Provides
    @Singleton
    fun movieDetailsService(): MoviesDetailsService =
        Retrofit.Builder()
            .baseUrl(BASE_URL_MOVIES)
            .addConverterFactory(GsonConverterFactory.create())
            .client(interceptorClient())
            .build()
            .create(MoviesDetailsService::class.java)


    @Provides
    @Singleton
    fun addMovieDetailsRepo(moviesDetailsService: MoviesDetailsService): MoviesDetailsRepo =
        MoviesDetailsRepoImplement(moviesDetailsService)


    @Provides
    @Singleton
    fun movieDetailsSimilarService(): MoviesDetailsSimilarService =
        Retrofit.Builder()
            .baseUrl(BASE_URL_MOVIES)
            .addConverterFactory(GsonConverterFactory.create())
            .client(interceptorClient())
            .build()
            .create(MoviesDetailsSimilarService::class.java)


    @Provides
    @Singleton
    fun addMovieDetailsSimilarRepo(moviesDetailsSimilarService: MoviesDetailsSimilarService): MoviesDetailsSimilarRepo =
        MoviesDetailsSimilarRepoImplement(moviesDetailsSimilarService)


    @Provides
    @Singleton
    fun showListService(): ShowsListService =
        Retrofit.Builder()
            .baseUrl(BASE_URL_SHOWS)
            .addConverterFactory(GsonConverterFactory.create())
            .client(interceptorClient())
            .build()
            .create(ShowsListService::class.java)


    @Provides
    @Singleton
    fun addShowListRepo(showListService: ShowsListService): ShowListRepo =
        ShowListRepoImplement(showListService)
}