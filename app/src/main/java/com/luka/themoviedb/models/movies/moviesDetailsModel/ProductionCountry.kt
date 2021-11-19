package com.luka.themoviedb.models.movies.moviesDetailsModel


import com.google.gson.annotations.SerializedName

data class ProductionCountry(
    @SerializedName("name")
    val name: String?
)