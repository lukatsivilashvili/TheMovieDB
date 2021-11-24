package com.luka.themoviedb.adapters.moviesPagination.detailsPagination

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.luka.themoviedb.databinding.ItemRecyclerSimilarBinding
import com.luka.themoviedb.extensions.loadImageList
import com.luka.themoviedb.models.movies.moviesDetailsSimilarModel.MoviesDetailsSimilarsFinal
import com.luka.themoviedb.utils.MoviesSimilarComparator
import com.luka.themoviedb.utils.OnItemClickListener

class MoviesDetailsSimilarRecyclerAdapter(private val itemClickListener: OnItemClickListener) :
    PagingDataAdapter<MoviesDetailsSimilarsFinal, MoviesDetailsSimilarRecyclerAdapter.MyViewHolder>(
        MoviesSimilarComparator
    ) {

    inner class MyViewHolder(private val binding: ItemRecyclerSimilarBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind() {

            val similars = getItem(absoluteAdapterPosition)
            binding.ivPosterSimilar.loadImageList(similars?.urlGenerator())
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
            ItemRecyclerSimilarBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind()
    }
}