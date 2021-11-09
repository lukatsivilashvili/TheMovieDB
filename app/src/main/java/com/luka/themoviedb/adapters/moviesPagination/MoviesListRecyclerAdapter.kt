package com.luka.themoviedb.adapters.moviesPagination

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.luka.themoviedb.R
import com.luka.themoviedb.databinding.ItemRecyclerMoviesBinding
import com.luka.themoviedb.extensions.loadBackground
import com.luka.themoviedb.extensions.loadImage
import com.luka.themoviedb.models.movies.Result
import java.util.*


class MoviesListRecyclerAdapter(val context: Context) :
    PagingDataAdapter<Result, MoviesListRecyclerAdapter.MyViewHolder>(MoviesComparator) {

    inner class MyViewHolder(private val binding: ItemRecyclerMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {

            val movies = getItem(absoluteAdapterPosition)
            binding.tvReleaseDate.text = context.getString(R.string.releaseDate, movies?.releaseDate)
            binding.tvTitle.text = movies?.title
            binding.tvLanguage.text = context.getString(R.string.language,
                movies?.originalLanguage?.uppercase(Locale.getDefault())
            )
            binding.tvRating.text = movies?.voteAverage.toString()
            binding.ivBackdrop.loadImage(movies?.urlGenerator())
            binding.tvRating.loadBackground(movies?.voteAverage)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MyViewHolder(
            ItemRecyclerMoviesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind()
    }

    object MoviesComparator : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }

    }


}