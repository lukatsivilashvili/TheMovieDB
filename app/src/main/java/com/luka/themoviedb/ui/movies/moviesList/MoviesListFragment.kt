package com.luka.themoviedb.ui.movies.moviesList

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.luka.themoviedb.R

class MoviesListFragment : Fragment() {

    companion object {
        fun newInstance() = MoviesListFragment()
    }

    private lateinit var viewModel: MoviesListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movies_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MoviesListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}