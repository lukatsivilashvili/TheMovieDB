package com.luka.themoviedb.models.movies.moviesListSearchModel


import com.google.gson.annotations.SerializedName

data class MoviesListSearchFirst(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val moviesListSearchFinals: List<MoviesListSearchFinal>?,
)