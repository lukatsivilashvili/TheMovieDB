package com.luka.themoviedb.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.luka.themoviedb.R
import com.luka.themoviedb.adapters.ViewPagerAdapter
import com.luka.themoviedb.base.BaseFragment
import com.luka.themoviedb.databinding.FragmentNavHostBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavHostFragment : BaseFragment<FragmentNavHostBinding>(FragmentNavHostBinding::inflate){

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun initialize(inflater: LayoutInflater, container: ViewGroup?) {
        init()
    }

    private fun init() {





        viewPager = binding.viewPager
        tabLayout = binding.tabLayout

        val viewPagerFragmentAdapter = ViewPagerAdapter(activity as AppCompatActivity)
        viewPager.adapter = viewPagerFragmentAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->

            when (position) {
                0 -> {
                    tab.text = "Movies"
                    tab.setIcon(R.drawable.ic_movie)
                }
                1 -> {
                    tab.text = "Shows"
                    tab.setIcon(R.drawable.ic_shows)
                }
            }
        }.attach()

    }



}