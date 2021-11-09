package com.luka.themoviedb.di

import androidx.viewbinding.BuildConfig
import com.luka.themoviedb.repository.movies.moviesList.MovieListRepoImplement
import com.luka.themoviedb.repository.movies.moviesList.MoviesListRepo
import com.luka.themoviedb.retrofit.MovieListService
import com.luka.themoviedb.utils.Constants.BASE_URL
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
    fun movieListService(): MovieListService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(interceptorClient())
            .build()
            .create(MovieListService::class.java)


    @Provides
    @Singleton
    fun addMovieListRepo(movieListService: MovieListService): MoviesListRepo =
        MovieListRepoImplement(movieListService)
}