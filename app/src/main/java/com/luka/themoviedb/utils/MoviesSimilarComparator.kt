package com.luka.themoviedb.utils

import androidx.recyclerview.widget.DiffUtil
import com.luka.themoviedb.models.movies.moviesDetailsSimilarModel.MoviesDetailsSimilarsFinal

object MoviesSimilarComparator : DiffUtil.ItemCallback<MoviesDetailsSimilarsFinal>() {
    override fun areItemsTheSame(oldItem: MoviesDetailsSimilarsFinal, newItem: MoviesDetailsSimilarsFinal): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: MoviesDetailsSimilarsFinal, newItem: MoviesDetailsSimilarsFinal): Boolean {
        return oldItem == newItem
    }

}