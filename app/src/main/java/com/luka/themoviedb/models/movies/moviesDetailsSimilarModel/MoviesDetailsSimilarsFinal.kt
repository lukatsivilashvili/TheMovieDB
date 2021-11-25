package com.luka.themoviedb.models.movies.moviesDetailsSimilarModel


import com.google.gson.annotations.SerializedName
import com.luka.themoviedb.utils.Constants

data class MoviesDetailsSimilarsFinal(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("poster_path")
    val posterPath: String?
){
    fun urlGenerator(): String{
        return Constants.IMAGE_URL_W500 + posterPath
    }
}