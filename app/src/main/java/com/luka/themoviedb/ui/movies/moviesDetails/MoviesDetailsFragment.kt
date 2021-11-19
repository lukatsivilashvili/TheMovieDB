package com.luka.themoviedb.ui.movies.moviesDetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.luka.themoviedb.base.BaseFragment
import com.luka.themoviedb.databinding.MoviesDetailsFragmentBinding
import com.luka.themoviedb.extensions.loadImageDetails
import com.luka.themoviedb.retrofit.NetworkHandler
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesDetailsFragment :
    BaseFragment<MoviesDetailsFragmentBinding>(MoviesDetailsFragmentBinding::inflate) {

    private val viewModel: MoviesDetailsViewModel by viewModels()

    override fun initialize(inflater: LayoutInflater, container: ViewGroup?) {
        init()
    }

    private fun init() {
        initDetails()
        observes()
    }


    private fun initDetails() {
        val args: MoviesDetailsFragmentArgs by navArgs()
        val id = args.id

        id?.toInt()?.let { viewModel.getMovieDetails(it) }

    }

    private fun observes() {
        viewModel.movieDetailsData.observe(viewLifecycleOwner, {
            when(it){
                is NetworkHandler.Success -> {
                    binding.tvMovieDetailsTitle.text = it.data?.title
                    binding.tvMovieDetailsOverview.text = it.data?.overview
                    binding.ivMovieDetails.loadImageDetails(it.data?.urlGenerator())
                    binding.pbMoviesDetails.visibility = View.GONE
                }
                is NetworkHandler.Error -> Toast.makeText(requireContext(), "Error!", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }


}