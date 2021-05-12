package com.joenjogu.nexflix.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.joenjogu.nexflix.R
import com.joenjogu.nexflix.adapters.FragmentAdapter

class ViewPagerFragment : Fragment() {

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

        initToolbar(toolbar)
    }

    private fun initToolbar(toolbar: MaterialToolbar) {
        toolbar.inflateMenu(R.menu.top_app_bar)
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

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return NavigationUI.onNavDestinationSelected(item, findNavController())
//                || super.onOptionsItemSelected(item)
//    }
}
