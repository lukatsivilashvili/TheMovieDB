package com.luka.themoviedb.models.shows.showsListModel


import com.google.gson.annotations.SerializedName
import com.luka.themoviedb.utils.Constants

data class ShowsListFinal(
    @SerializedName("first_air_date")
    val firstAirDate: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("original_language")
    val originalLanguage: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("vote_average")
    val voteAverage: Double?
){
    fun urlGenerator(): String{
        return Constants.IMAGE_URL_W500 + posterPath
    }
}