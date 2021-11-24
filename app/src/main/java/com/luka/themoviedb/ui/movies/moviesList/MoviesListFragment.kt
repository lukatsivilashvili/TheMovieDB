package com.luka.themoviedb.ui.movies.moviesList

import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.luka.themoviedb.R
import com.luka.themoviedb.adapters.moviesPagination.listPagination.MoviesListRecyclerAdapter
import com.luka.themoviedb.base.BaseFragment
import com.luka.themoviedb.databinding.MoviesListFragmentBinding
import com.luka.themoviedb.ui.NavHostFragmentDirections
import com.luka.themoviedb.utils.NetworkCheck
import com.luka.themoviedb.utils.OnItemClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesListFragment :
    BaseFragment<MoviesListFragmentBinding>(MoviesListFragmentBinding::inflate),
    SearchView.OnQueryTextListener {

    private val viewModel: MoviesListViewModel by viewModels()
    private lateinit var myAdapter: MoviesListRecyclerAdapter
    private lateinit var networkChecker: NetworkCheck

    override fun initialize(inflater: LayoutInflater, container: ViewGroup?) {
        setHasOptionsMenu(true)
        init()
    }

    private fun init() {
        checkNetwork()
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


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
        val search = menu.findItem(R.id.mnSearch)
        val searchView = search?.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)

        val iconSearch: ImageView =
            searchView?.findViewById(androidx.appcompat.R.id.search_button)!!
        val iconSearchClose: ImageView =
            searchView.findViewById(androidx.appcompat.R.id.search_close_btn)!!
        val iconSearchComplete: ImageView =
            searchView.findViewById(androidx.appcompat.R.id.search_go_btn)!!
        iconSearch.setImageResource(R.drawable.ic_search)
        iconSearchClose.setImageResource(R.drawable.ic_close)
        iconSearchComplete.setImageResource(R.drawable.ic_go)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {

            viewModel.getMoviesListSearch(query)

            viewModel.getMoviesListSearch(query).observe(viewLifecycleOwner, {
                myAdapter.submitData(lifecycle, it)
            })
        }

        if (query.isNullOrEmpty()) {
            observe()
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            viewModel.getMoviesListSearch(newText)

            viewModel.getMoviesListSearch(newText).observe(viewLifecycleOwner, {
                myAdapter.submitData(lifecycle, it)
            })
        }

        if (newText.isNullOrEmpty()) {
            observe()
        }
        return true
    }

    private fun checkNetwork() {
        networkChecker = NetworkCheck(requireActivity().application)
        networkChecker.observe(this, { isConnected ->
            if (isConnected) {
                observe()
            } else {
                showErrorDialog("Error", "No Network")
            }
        })
    }


}