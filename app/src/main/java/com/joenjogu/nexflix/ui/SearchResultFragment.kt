package com.joenjogu.nexflix.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.joenjogu.nexflix.R
import com.joenjogu.nexflix.adapters.FavouriteMovieAdapter
import com.joenjogu.nexflix.databinding.FragmentFavouriteBinding
import com.joenjogu.nexflix.viewmodels.ViewPagerViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class SearchResultFragment : Fragment() {

    private lateinit var dataBinding: FragmentFavouriteBinding
    private val viewPagerViewModel: ViewPagerViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_favourite, container, false)
        dataBinding.favouriteToolbar.title = getString(R.string.search_results)

        val adapter = FavouriteMovieAdapter()
        dataBinding.favouriteRecyclerview.adapter = adapter

        viewPagerViewModel.searchResults?.observe(viewLifecycleOwner) { searchResults ->
            if (!searchResults.isNullOrEmpty()) {
                adapter.submitList(searchResults)
            } else {
                dataBinding.favouriteRecyclerview.visibility = View.GONE
                dataBinding.lottieNoResults.visibility = View.VISIBLE
            }
        }

        dataBinding.favouriteToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        return dataBinding.root
    }
}