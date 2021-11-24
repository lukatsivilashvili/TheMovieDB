package com.luka.themoviedb.models.movies.moviesDetailsModel


import com.google.gson.annotations.SerializedName
import com.luka.themoviedb.utils.Constants

data class MoviesDetailsFinal(
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("original_language")
    val originalLanguage: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("poster_path")
    val posterPath: Any?,
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany>?,
    @SerializedName("genres")
    val genres: List<Genre>?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("revenue")
    val revenue: Int?,
    @SerializedName("runtime")
    val runtime: Int?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("video")
    val video: Boolean?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("tagline")
    val tagline: String?
){
    fun urlBackdropGenerator(): String{
        return Constants.IMAGE_URL_ORIGINAL + backdropPath
    }

    fun urlPosterGenerator(): String{
        return Constants.IMAGE_URL_W500 + posterPath
    }
}