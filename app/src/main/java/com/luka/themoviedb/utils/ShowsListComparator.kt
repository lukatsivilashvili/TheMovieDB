package com.luka.themoviedb.utils

import androidx.recyclerview.widget.DiffUtil
import com.luka.themoviedb.models.shows.showsListModel.ShowsListFinal

object ShowsListComparator : DiffUtil.ItemCallback<ShowsListFinal>() {
    override fun areItemsTheSame(oldItem: ShowsListFinal, newItem: ShowsListFinal): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: ShowsListFinal, newItem: ShowsListFinal): Boolean {
        return oldItem == newItem
    }

}