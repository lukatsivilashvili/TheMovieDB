package com.luka.themoviedb.models.shows.showsListModel


import com.google.gson.annotations.SerializedName

data class ShowsListFirst(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val showsListFinals: List<ShowsListFinal>?
)