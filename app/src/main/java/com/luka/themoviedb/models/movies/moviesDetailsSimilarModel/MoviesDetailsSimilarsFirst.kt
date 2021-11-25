package com.luka.themoviedb.models.movies.moviesDetailsSimilarModel


import com.google.gson.annotations.SerializedName

data class MoviesDetailsSimilarsFirst(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val moviesDetailsSimilarsFinals: List<MoviesDetailsSimilarsFinal>?
)