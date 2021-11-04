package com.luka.themoviedb.adapters

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.luka.themoviedb.ui.movies.moviesList.MoviesListFragment
import com.luka.themoviedb.ui.shows.showsList.ShowsListFragment

class ViewPagerAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> MoviesListFragment()
            1 -> ShowsListFragment()
            else -> MoviesListFragment()
        }
    }
}