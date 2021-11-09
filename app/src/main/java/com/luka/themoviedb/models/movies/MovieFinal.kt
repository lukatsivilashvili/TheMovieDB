package com.luka.themoviedb.models.movies


import com.google.gson.annotations.SerializedName

data class MovieFinal(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: List<Result>?
)