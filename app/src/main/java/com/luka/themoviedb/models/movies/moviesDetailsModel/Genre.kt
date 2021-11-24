package com.luka.themoviedb.models.movies.moviesDetailsModel


import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("name")
    val name: String?
)