package com.luka.themoviedb.ui.shows.showsList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.luka.themoviedb.adapters.showsPagination.listPagination.ShowsListRecyclerAdapter
import com.luka.themoviedb.base.BaseFragment
import com.luka.themoviedb.databinding.ShowsListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ShowsListFragment :
    BaseFragment<ShowsListFragmentBinding>(ShowsListFragmentBinding::inflate) {

    private val viewModel: ShowsListViewModel by viewModels()
    private lateinit var myAdapter: ShowsListRecyclerAdapter

    override fun initialize(inflater: LayoutInflater, container: ViewGroup?) {
        init()
    }

    private fun init() {
        initRecycler()
        observe()
    }

    private fun initRecycler() {
        myAdapter = ShowsListRecyclerAdapter(requireContext())

        binding.myRecyclerShowsList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = myAdapter



        }

        myAdapter.addLoadStateListener {
            binding.myProgressBarCharacters.isVisible = it.refresh is LoadState.Loading
        }
    }

    private fun observe() {
        viewModel.showsList.observe(viewLifecycleOwner, {
            myAdapter.submitData(lifecycle, it)
        })
    }


}