package com.luka.themoviedb.adapters.showsPagination.listPagination

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.luka.themoviedb.R
import com.luka.themoviedb.adapters.moviesPagination.listPagination.MoviesListRecyclerAdapter
import com.luka.themoviedb.databinding.ItemRecyclerMoviesListBinding
import com.luka.themoviedb.databinding.ItemRecyclerShowsListBinding
import com.luka.themoviedb.extensions.loadBackground
import com.luka.themoviedb.extensions.loadImage
import com.luka.themoviedb.models.movies.moviesListModel.MoviesListFinal
import com.luka.themoviedb.models.movies.showsListModel.ShowsListFinal
import java.util.*


class ShowsListRecyclerAdapter(val context: Context) :
    PagingDataAdapter<ShowsListFinal, ShowsListRecyclerAdapter.MyViewHolder>(ShowsComparator) {

    inner class MyViewHolder(private val binding: ItemRecyclerShowsListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {

            val shows = getItem(absoluteAdapterPosition)
            binding.tvFirstAirDateDate.text = context.getString(R.string.first_air_date, shows?.firstAirDate)
            binding.tvTitle.text = shows?.name
            binding.tvLanguage.text = context.getString(R.string.language, shows?.originalLanguage?.uppercase(Locale.getDefault()))
            binding.ivBackdropShowsList.loadImage(shows?.urlGenerator())
            binding.tvRating.loadBackground(shows?.voteAverage)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MyViewHolder(
            ItemRecyclerShowsListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind()
    }

    object ShowsComparator : DiffUtil.ItemCallback<ShowsListFinal>() {
        override fun areItemsTheSame(oldItem: ShowsListFinal, newItem: ShowsListFinal): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: ShowsListFinal, newItem: ShowsListFinal): Boolean {
            return oldItem == newItem
        }

    }


}