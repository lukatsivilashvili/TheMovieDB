package com.luka.themoviedb.ui.movies.moviesList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.luka.themoviedb.adapters.moviesPagination.listPagination.MoviesListRecyclerAdapter
import com.luka.themoviedb.base.BaseFragment
import com.luka.themoviedb.databinding.MoviesListFragmentBinding
import com.luka.themoviedb.ui.NavHostFragmentDirections
import com.luka.themoviedb.utils.OnItemClickListener
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
        myAdapter = MoviesListRecyclerAdapter(requireContext(), object : OnItemClickListener {
            override fun clickItem(position: Int, id: String) {
                val action =
                    NavHostFragmentDirections.actionNavHostFragmentToMoviesDetailsFragment(id)
                view?.let { Navigation.findNavController(it).navigate(action) }
            }
        })

        binding.rvMoviesList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = myAdapter
        }

        myAdapter.addLoadStateListener {
            binding.pbMoviesList.isVisible = it.refresh is LoadState.Loading
        }
    }

    private fun observe() {
        viewModel.moviesList.observe(viewLifecycleOwner, {
            myAdapter.submitData(lifecycle, it)
        })
    }


}