package com.luka.themoviedb.models.movies.moviesListModel


import com.google.gson.annotations.SerializedName
import com.luka.themoviedb.utils.Constants.IMAGE_URL_W500

data class MoviesListFinal(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("original_language")
    val originalLanguage: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("vote_average")
    val voteAverage: Double?
){
    fun urlGenerator(): String{
        return IMAGE_URL_W500 + posterPath
    }
}