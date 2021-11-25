package com.luka.themoviedb.adapters.showsPagination.listPagination

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.luka.themoviedb.R
import com.luka.themoviedb.databinding.ItemRecyclerListBinding
import com.luka.themoviedb.extensions.loadBackground
import com.luka.themoviedb.extensions.loadImageList
import com.luka.themoviedb.models.shows.showsListModel.ShowsListFinal
import com.luka.themoviedb.utils.ShowsListComparator
import java.util.*


class ShowsListRecyclerAdapter(val context: Context) :
    PagingDataAdapter<ShowsListFinal, ShowsListRecyclerAdapter.MyViewHolder>(ShowsListComparator) {

    inner class MyViewHolder(private val binding: ItemRecyclerListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            val shows = getItem(absoluteAdapterPosition)
            binding.tvReleaseDate.text = context.getString(R.string.first_air_date, shows?.firstAirDate)
            binding.tvTitle.text = shows?.name
            binding.tvLanguage.text = context.getString(R.string.language, shows?.originalLanguage?.uppercase(Locale.getDefault()))
            binding.ivBackdrop.loadImageList(shows?.urlGenerator())
            binding.tvRating.loadBackground(shows?.voteAverage)
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