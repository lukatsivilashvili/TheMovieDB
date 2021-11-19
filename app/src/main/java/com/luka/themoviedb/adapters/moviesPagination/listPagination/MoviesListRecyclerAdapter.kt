package com.luka.themoviedb.adapters.moviesPagination.listPagination

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.luka.themoviedb.R
import com.luka.themoviedb.databinding.ItemRecyclerListBinding
import com.luka.themoviedb.extensions.loadBackground
import com.luka.themoviedb.extensions.loadImageList
import com.luka.themoviedb.models.movies.moviesListModel.MoviesListFinal
import com.luka.themoviedb.utils.MoviesListComparator
import com.luka.themoviedb.utils.OnItemClickListener
import java.util.*


class MoviesListRecyclerAdapter(
    private val context: Context,
    private val itemClickListener: OnItemClickListener
) :
    PagingDataAdapter<MoviesListFinal, MoviesListRecyclerAdapter.MyViewHolder>(MoviesListComparator) {

    inner class MyViewHolder(private val binding: ItemRecyclerListBinding) :
        RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {

        fun bind() {

            val movies = getItem(absoluteAdapterPosition)
            binding.tvReleaseDate.text =
                context.getString(R.string.releaseDate, movies?.releaseDate)
            binding.tvTitle.text = movies?.title
            binding.tvLanguage.text = context.getString(
                R.string.language,
                movies?.originalLanguage?.uppercase(Locale.getDefault())
            )
            binding.ivBackdrop.loadImageList(movies?.urlGenerator())
            binding.tvRating.loadBackground(movies?.voteAverage)
            binding.root.setOnClickListener(this)

        }

        override fun onClick(p0: View?) {
            itemClickListener.clickItem(
                bindingAdapterPosition,
                getItem(absoluteAdapterPosition)?.id.toString()
            )
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MyViewHolder(
            ItemRecyclerListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind()
    }
}