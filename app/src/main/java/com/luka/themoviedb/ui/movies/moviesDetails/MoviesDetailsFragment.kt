package com.luka.themoviedb.ui.movies.moviesDetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.luka.themoviedb.R

class MoviesDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = MoviesDetailsFragment()
    }

    private lateinit var viewModel: MoviesDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movies_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MoviesDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}