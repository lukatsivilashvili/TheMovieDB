package com.luka.themoviedb.ui.shows.showsDetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.luka.themoviedb.R

class ShowsDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = ShowsDetailsFragment()
    }

    private lateinit var viewModel: ShowsDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.shows_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ShowsDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}