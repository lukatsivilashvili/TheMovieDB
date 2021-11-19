package com.luka.themoviedb.utils

import androidx.recyclerview.widget.DiffUtil
import com.luka.themoviedb.models.movies.moviesListModel.MoviesListFinal

object MoviesListComparator : DiffUtil.ItemCallback<MoviesListFinal>() {
    override fun areItemsTheSame(oldItem: MoviesListFinal, newItem: MoviesListFinal): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: MoviesListFinal, newItem: MoviesListFinal): Boolean {
        return oldItem == newItem
    }

}