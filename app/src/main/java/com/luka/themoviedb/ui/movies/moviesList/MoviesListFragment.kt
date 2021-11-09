package com.luka.themoviedb.ui.movies.moviesList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.luka.themoviedb.adapters.moviesPagination.MoviesListRecyclerAdapter
import com.luka.themoviedb.base.BaseFragment
import com.luka.themoviedb.databinding.MoviesListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesListFragment :
    BaseFragment<MoviesListFragmentBinding>(MoviesListFragmentBinding::inflate) {

    private val viewModel: MoviesListViewModel by viewModels()
    private lateinit var myAdapter: MoviesListRecyclerAdapter

    override fun initialize(inflater: LayoutInflater, container: ViewGroup?) {
        init()
    }

    private fun init() {
        initRecycler()
        observe()
    }

    private fun initRecycler() {
        myAdapter = MoviesListRecyclerAdapter(requireContext())

        binding.myRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = myAdapter



        }

        myAdapter.addLoadStateListener {
            binding.myProgressBarCharacters.isVisible = it.refresh is LoadState.Loading
        }
    }

    private fun observe() {
        viewModel.moviesList.observe(viewLifecycleOwner, {
            myAdapter.submitData(lifecycle, it)
        })
    }


}