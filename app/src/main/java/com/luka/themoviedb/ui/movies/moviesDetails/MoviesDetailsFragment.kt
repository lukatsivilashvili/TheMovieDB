package com.luka.themoviedb.ui.movies.moviesDetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.luka.themoviedb.R
import com.luka.themoviedb.adapters.moviesPagination.detailsPagination.MoviesDetailsSimilarRecyclerAdapter
import com.luka.themoviedb.base.BaseFragment
import com.luka.themoviedb.databinding.MoviesDetailsFragmentBinding
import com.luka.themoviedb.extensions.loadBackground
import com.luka.themoviedb.extensions.loadImageDetails
import com.luka.themoviedb.extensions.loadImageList
import com.luka.themoviedb.extensions.quote
import com.luka.themoviedb.models.movies.moviesDetailsModel.MoviesDetailsFinal
import com.luka.themoviedb.retrofit.NetworkHandler
import com.luka.themoviedb.utils.OnItemClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesDetailsFragment :
    BaseFragment<MoviesDetailsFragmentBinding>(MoviesDetailsFragmentBinding::inflate) {

    private val viewModel: MoviesDetailsViewModel by viewModels()
    private lateinit var myAdapter: MoviesDetailsSimilarRecyclerAdapter


    override fun initialize(inflater: LayoutInflater, container: ViewGroup?) {
        setHasOptionsMenu(false)
        init()
    }

    private fun init() {
        initDetails()
        observes()
    }


    private fun initDetails() {
        val args: MoviesDetailsFragmentArgs by navArgs()
        val id = args.id

        initRecycler()

        id?.toInt()?.let { viewModel.getMovieDetails(it) }

    }

    private fun initRecycler() {
        myAdapter = MoviesDetailsSimilarRecyclerAdapter(object : OnItemClickListener {
            override fun clickItem(position: Int, id: String) {
                val action =
                    MoviesDetailsFragmentDirections.actionMoviesDetailsFragmentSelf(id)
                view?.let { Navigation.findNavController(it).navigate(action) }
            }
        })

        binding.rvMoviesDetailsSimilar.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = myAdapter

            val snapper: SnapHelper = LinearSnapHelper()
            snapper.attachToRecyclerView(binding.rvMoviesDetailsSimilar)
        }
    }

    private fun observes() {
        val args: MoviesDetailsFragmentArgs by navArgs()
        val id = args.id

        viewModel.movieDetailsData.observe(viewLifecycleOwner, {
            when (it) {
                is NetworkHandler.Success -> {
                    setDescription(it)
                }
                is NetworkHandler.Error -> Toast.makeText(
                    requireContext(),
                    "Error While Retrieving Data",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        })


        viewModel.getMoviesDetailsSimilar(id!!.toInt()).observe(viewLifecycleOwner, {
            myAdapter.submitData(lifecycle, it)
        })

    }

    private fun setDescription(info: NetworkHandler<MoviesDetailsFinal>) {
        binding.tvMovieDetailsTitle.text = info.data?.title
        binding.tvMovieDetailsOverview.text = info.data?.overview
        binding.ivMovieDetailsBackdrop.loadImageDetails(info.data?.urlBackdropGenerator())
        binding.pbMoviesDetails.visibility = View.GONE
        binding.tvMoviesDetailsRating.loadBackground(info.data?.voteAverage)

        if (info.data?.tagline != "") {
            info.data?.tagline?.let { it1 -> binding.tvMoviesDetailsTagline.quote(it1) }
        } else {
            binding.tvMoviesDetailsTagline.text = ""
        }

        binding.ivMovieDetailsPoster.loadImageList(info.data?.urlPosterGenerator())

        binding.tvMoviesDetailsDetailReleaseDate.text =
            context?.getString(R.string.releaseDate, info.data?.releaseDate)
        binding.tvMoviesDetailsDetailStatus.text =
            context?.getString(R.string.status, info.data?.status)
        binding.tvMoviesDetailsDetailRuntime.text =
            context?.getString(R.string.runtime, info.data?.runtime.toString())
        binding.tvMoviesDetailsDetailRevenue.text =
            context?.getString(R.string.revenue, info.data?.revenue.toString())

        getGenres(info)
    }

    private fun getGenres(info_genre: NetworkHandler<MoviesDetailsFinal>) {
        val genres = mutableListOf<String>()
        for (i in info_genre.data?.genres!!) {
            genres.add(i.name!!)
        }

        binding.tvMoviesDetailsDetailGenres.text =
            context?.getString(R.string.genres, genres.joinToString(", "))
    }


}