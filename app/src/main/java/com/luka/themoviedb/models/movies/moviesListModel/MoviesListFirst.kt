package com.luka.themoviedb.models.movies.moviesListModel


import com.google.gson.annotations.SerializedName

data class MoviesListFirst(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val moviesListFinals: List<MoviesListFinal>?
)