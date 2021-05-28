package com.joenjogu.nexflix.ui

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.joenjogu.nexflix.R
import com.joenjogu.nexflix.adapters.FragmentAdapter
import com.joenjogu.nexflix.viewmodels.ViewPagerViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ViewPagerFragment : Fragment(), SearchView.OnQueryTextListener {

    private val viewModel: ViewPagerViewModel by sharedViewModel()
    private var adapter: ArrayAdapter<String>? = null

    companion object {
        val TAG: String = ViewPagerFragment::class.java.name
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.top_app_bar, menu)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_viewpager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewPager = view.findViewById<ViewPager2>(R.id.viewpager)
        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout)
        val toolbar = view.findViewById<MaterialToolbar>(R.id.menu_app_bar)

        val fragmentAdapter = FragmentAdapter(childFragmentManager, lifecycle)
        viewPager.adapter = fragmentAdapter
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.popular)
                1 -> tab.text = getString(R.string.latest)
            }
            viewPager.setCurrentItem(0, true)
        }.attach()

//        initToolbar(toolbar)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val toolbar = view?.findViewById<MaterialToolbar>(R.id.menu_app_bar)

        if (toolbar != null) {
            initToolbar(toolbar)
        }
    }
    private fun initToolbar(toolbar: MaterialToolbar) {
        toolbar.inflateMenu(R.menu.top_app_bar)

        val searchManager = requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val search = toolbar.menu.findItem(R.id.menu_search)
        (search.actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
            setIconifiedByDefault(true)
            setOnQueryTextListener(this@ViewPagerFragment)
        }
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.favouriteFragment -> {
                    val direction = ViewPagerFragmentDirections.actionViewPagerFragmentToFavouriteFragment()
                    findNavController().navigate(direction)
                    true
                }
                else -> false
            }
        }
    }

    private fun searchMovies(movie: String) {
        viewModel.searchMovie(movie).observe(viewLifecycleOwner) {
            Toast.makeText(
                requireContext(),
                "got movies! ${it.size}",
                Toast.LENGTH_LONG
            ).show()
            val direction = ViewPagerFragmentDirections.actionViewPagerFragmentToSearchResultFragment()
            findNavController().navigate(direction)
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let {
            searchMovies(it)
        }

        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        adapter?.filter?.filter(newText)
        return true
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return NavigationUI.onNavDestinationSelected(item, findNavController())
//                || super.onOptionsItemSelected(item)
//    }
}
