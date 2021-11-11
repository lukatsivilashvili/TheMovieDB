package com.luka.themoviedb.adapters.moviesPagination.listPagination

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.luka.themoviedb.R
import com.luka.themoviedb.databinding.ItemRecyclerMoviesListBinding
import com.luka.themoviedb.extensions.loadBackground
import com.luka.themoviedb.extensions.loadImage
import com.luka.themoviedb.models.movies.moviesListModel.MoviesListFinal
import java.util.*


class MoviesListRecyclerAdapter(val context: Context) :
    PagingDataAdapter<MoviesListFinal, MoviesListRecyclerAdapter.MyViewHolder>(MoviesComparator) {

    inner class MyViewHolder(private val binding: ItemRecyclerMoviesListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {

            val movies = getItem(absoluteAdapterPosition)
            binding.tvReleaseDate.text = context.getString(R.string.releaseDate, movies?.releaseDate)
            binding.tvTitle.text = movies?.title
            binding.tvLanguage.text = context.getString(R.string.language,
                movies?.originalLanguage?.uppercase(Locale.getDefault())
            )
            binding.ivBackdrop.loadImage(movies?.urlGenerator())
            binding.tvRating.loadBackground(movies?.voteAverage)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MyViewHolder(
            ItemRecyclerMoviesListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind()
    }

    object MoviesComparator : DiffUtil.ItemCallback<MoviesListFinal>() {
        override fun areItemsTheSame(oldItem: MoviesListFinal, newItem: MoviesListFinal): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: MoviesListFinal, newItem: MoviesListFinal): Boolean {
            return oldItem == newItem
        }

    }


}