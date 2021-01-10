package com.joenjogu.nexflix.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.joenjogu.nexflix.R
import com.joenjogu.nexflix.adapters.FragmentAdapter

class ViewPagerFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_viewpager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewPager = view.findViewById<ViewPager2>(R.id.viewpager)
        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout)
        val fragmentManager = parentFragmentManager

        val fragmentAdapter = FragmentAdapter(fragmentManager, lifecycle)
        viewPager.adapter = fragmentAdapter
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        TabLayoutMediator(tabLayout, viewPager){ tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.popular)
                1 -> tab.text = getString(R.string.latest)
            }
            viewPager.setCurrentItem(0, true)
        }.attach()
    }
}